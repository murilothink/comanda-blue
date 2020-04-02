import React, { useState, useEffect } from 'react';
import api from '../Services/api';
import Redirector from '../Services/redirector';

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';

// Para alertas
import MessageAlert from '../Components/MessageAlert';

// Para utilizar tabela, ref: https://material-ui.com/components/tables/
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
// Para utilizar select
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import InputAdornment from '@material-ui/core/InputAdornment';
import OutlinedInput from '@material-ui/core/OutlinedInput';

import '../style.css';

export default function TelaPagamento(props){
    const [mount, setMount] = React.useState(false);

    useEffect(() => {
        if(!mount){
            let redirector =  new Redirector(props);
            redirector.checkLogado();
            redirector.checkComanda();

            setMount(true);
        }
    });

    return (
    <div class="wrapper">
        <div class="main_collumn">
            
                <Button onClick={()=>{props.history.push("/menu")}} variant="contained" color="primary" style={{background: '#2d9bf0', color: 'white', margin:"5px", position:"fixed", marginTop:"16px"}}>
                    Voltar ao menu
                </Button>

            <Grid container spacing={3}  style={{maxWidth:"100%"}}>
                <Grid item xs={12} sm={6}>
                    <ComponenteExtrato {...props}/>
                </Grid>
        
                <Grid xs={12} sm={6}>
                    <ComponentePagamento {...props}/>
                </Grid>
            </Grid>
       </div>
    </div>
    )
}

const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});

// Converte valor para R$XX.XX
function ccyFormat(num) {
    if(!num) return "R$0,0";
    return `R\$${num.toFixed(2)}`;
}

// Calcula o total, baseado na tabela
// TODO entender melhor o reduce
function calculaSubtotal(items) {
    if(!items) return null;
    return items.map(({ valorTotal }) => valorTotal).reduce((sum, i) => sum + i, 0);
}

// Calcula o total pago, baseado na tabela
function somaValoresPagos(items) {
    if(!items) return null;
    return items.map(({ valorPago }) => valorPago).reduce((sum, i) => sum + i, 0);
}

let invoiceTotal = 0;

function ComponentePagamento(props) {
    const classes = useStyles();

    const [state, setState] = useState({
        pagamentos: null
    });

    //Pega os pagamentos da api
    const getPagamentos = () => {
        const url = "/comanda/"+props.userLogin.idComanda+"/pagamento";
        
        api.get(url)
        .then(response => {
            setState({
                pagamentos: response.data
            });   
        })
        .catch(error => {
            console.log(error);
        });
    }

    //TODO
    const postPagamento = (valor) =>{
        const url = "/comanda/"+props.userLogin.idComanda+"/pagar";

        const options = {
            headers: {
                'COMANDA-BLUE-CLIENTE': props.userLogin.comandaBlueCliente
            }
        };

        const data = {
            valorPago: valor
        }

        api.post(url, data, options)
        .then(response => {
            console.log(response);
            getPagamentos();
        })
        .catch(err =>{
            console.log(err)
        })
    }

    //soma os valores pagos de state.pagamentos
    const totalPago = () =>{
        return somaValoresPagos(state.pagamentos);
    }

    //Usa o invoice ainda
    const totalAPagar = () =>{
        return invoiceTotal - totalPago();
    }

    useEffect(()=>{
        if(!state.pagamentos){
            getPagamentos()
        }
    }); //didUpdate + didMount

    //Ao clicar em pagar posta um pagamento e
    //recarrega os pagamentos da api
    const handleClickPagar = () =>{
        const valor = document.getElementById("input-name").value;
        
        postPagamento(valor);
    }

    return (
    <Grid id="telaPagamento" style={{marginTop: "15px"}}>        
        <Grid
            container style={{ height: "10%"}}
            direction="row"
            spacing={0}
            align="center"
            justify="center" 
        >
            <Grid
                container                    
                spacing={0}
                align="center"
                justify="center"
                direction="column" item xs={12} sm={6} id="titulo"
            >
                <h1>PAGAMENTO</h1>
            </Grid>
        </Grid>

        <Grid
            container style={{ height: "5%" }}
            direction="row"
            spacing={0}
            align="center"
            justify="center"
        >
            <Grid
            container                    
            spacing={0}
            align="center"
            justify="center"
            direction="column" item xs={12} sm={6}
            >
                <FormControl variant="outlined" style={{marginTop: "10px"}}>            
                    <InputLabel htmlFor="input-name">Valor</InputLabel>
                    <OutlinedInput
                    id="input-name"
                    type='name'                     
                    labelWidth={20}
                    />
                </FormControl>
            </Grid>
               
            <Grid
                container
                spacing={0}
                justify="center"
                direction="column" item xs={12} sm={6}
            >

                <Button onClick={handleClickPagar} variant="contained" color="primary" style={{background: '#009900', color: 'white'}}>
                    PAGAR
                </Button>
            </Grid>
        </Grid>

        <Grid
            container
            style={{ height: "85%" }}
            direction="row"
            spacing={0}
            align="center"
            justify="center"
        >  

            <Grid
                container                    
                spacing={0}
                align="center"
                justify="center"
                direction="column" item xs={18} sm={18} lg={18} id="tabelaExtrato"
            >
                <div className="overflow" style={{maxHeight:"325px"}}>
                    <TableContainer>
                        <Table className={classes.table} aria-label="tabela pagamento">

                            <TableHead>
                                <TableRow>
                                    <TableCell width="50px"><b>Cliente</b></TableCell>
                                    <TableCell width="50px"><b>Valor Pago (R$)</b></TableCell>
                                </TableRow>
                            </TableHead>

                            <TableBody>

                                {(state.pagamentos)?state.pagamentos.map((row) => (
                                    <TableRow key={row.id}>
                                        <TableCell width="50px">{row.cliente.nome}</TableCell>
                                        <TableCell width="50px">{ccyFormat(row.valorPago)}</TableCell>
                                    </TableRow>
                                )):(<></>)}

                                <TableRow>
                                    <TableCell width="200px"><b>Total Pago</b></TableCell>
                                <TableCell >{ccyFormat(totalPago())}</TableCell>
                                </TableRow>

                                <TableRow>
                                    <TableCell width="200px"><b>Total a Pagar</b></TableCell>
                                <TableCell>{ccyFormat(totalAPagar())}</TableCell>
                                </TableRow>

                            </TableBody>

                        </Table>
                    </TableContainer>
                </div>                   
            </Grid>   

        </Grid>

    </Grid>
  );
}

function ComponenteExtrato(props) {
    const classes = useStyles();

    const [state, setState] = useState({
        pedidos: null,
        totalMesa: 0
    });

    const getPedidos = () => {
        //const email = "aguiar@ciandt.com"
        const email = props.userLogin.email;
        console.log(email);

        const url = "/estabelecimento/"+ props.userLogin.idEstabelecimento +
                    "/mesas/"+props.userLogin.idMesa+
                    "/comandas/"+props.userLogin.idComanda+
                    "/pedidos";
                        
        api.get(url)
        .then(response => {
            console.log("pedidos:");
            console.log(response);
            setState({
                pedidos: response.data.filter((value)=>{
                    return value.clienteSolicitante.email == email;
                }),
                totalMesa: calculaSubtotal(response.data)
            });   
        })
        .catch(error => {
            console.log(error);
        });
    }

    const subtotal = () => {
        invoiceTotal = state.totalMesa;
        return calculaSubtotal(state.pedidos);
    }

    useEffect(()=>{
        if(!state.pedidos){
            getPedidos();
        }
    });

    return (
    <Grid id="telaExtrato">        
        <Grid
            container 
            direction="row"
            spacing={0}
            align="center"
            justify="center" 
        >
            <Grid
                container                    
                spacing={0}
                align="center"
                justify="center"
                direction="column" item xs={12} sm={6} id="titulo"
                style={{ margin:"0", padding:"0" }}
            >
                <h1>EXTRATO</h1>

                Nome:    {props.userLogin.nome}

                {/*
                    jsonComanda.itemPedido.map((item, index) => {
                        if (index == 0) {
                            return item.clienteSolicitante.nome
                        }
                    })
                */}
            </Grid>
        </Grid>

        <Grid
            container
            style={{ height: "85%" }}
            direction="row"
            spacing={0}
            align="center"
            justify="center"
        >  

            <Grid
                container                    
                spacing={0}
                align="center"
                justify="center"
                direction="column" item xs={18} sm={18} lg={18} id="tabelaExtrato"
            >
                <div className="overflow" style={{maxHeight:"350px"}}>
                    <TableContainer>
                        <Table className={classes.table} aria-label="tabela extrato">

                            <TableHead>
                                <TableRow>
                                    <TableCell><b>Item</b></TableCell>
                                    <TableCell align="right"><b>Quantidade</b></TableCell>
                                    <TableCell align="right"><b>Valor Unitário (R$)</b></TableCell>
                                    <TableCell align="right"><b>Valor Total (R$)</b></TableCell>
                                    <TableCell align="right"><b>...</b></TableCell>
                                </TableRow>
                            </TableHead>

                            <TableBody>

                                {(state.pedidos)? state.pedidos.map((row) => (
                                    <TableRow key={row.id}>
                                        <TableCell>{row.produto.nome}</TableCell>
                                        <TableCell align="right">{row.quantidade}</TableCell>
                                        <TableCell align="right">{ccyFormat(row.valorUnitario)}</TableCell>
                                        <TableCell align="right">{ccyFormat(row.valorTotal)}</TableCell>
                                    </TableRow>
                                )):(<></>)}

                                <TableRow>
                                    <TableCell colSpan={3}><b>Subtotal</b></TableCell>
                                    <TableCell align="right">{ccyFormat(subtotal())}</TableCell>
                                </TableRow>

                                <TableRow>
                                    <TableCell colSpan={3}><b>Total da Mesa</b></TableCell>
                                    <TableCell align="right">{ccyFormat(state.totalMesa)}</TableCell>
                                </TableRow>

                            </TableBody>

                        </Table>
                    </TableContainer>
                </div>                    
            </Grid>   

        </Grid>

    </Grid>
  );
}