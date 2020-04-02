import React, { useState } from 'react';
import api from '../Services/api';

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
    const { userLogin, setUserLogin } = React.useState(props.userLogin);

    return (
    <div class="wrapper">
        <div class="main_collumn">
            
                <Button variant="contained" color="primary" style={{background: '#2d9bf0', color: 'white', margin:"5px", position:"fixed", marginTop:"16px"}}>
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
    return `R${num.toFixed(2)}`;
}

// Calcula valor total linha
function priceRow(qtde, valorUnitario) {
    return qtde * valorUnitario;
}

// Calcula valor total pago
function priceRowPagamento(valorPago) {
    return valorPago + valorPago;
}

// Cria linha baseado nos parametros fornecidos
function createRow(idPedido, cliente, item, qtde, valorUnitario) {
    const valorTotal = priceRow(valorUnitario, qtde);
    return { idPedido, cliente, item, qtde, valorUnitario, valorTotal };
}

// Cria linha baseado nos parametros fornecidos
function createRowPagamento(idPagamento, cliente, valorPago) {
    const valorTotal = priceRowPagamento(valorPago);
    return { idPagamento, cliente, valorPago };
}

// Calcula o total, baseado na tabela
// TODO entender melhor o reduce
function subtotal(items) {
    return items.map(({ valorTotal }) => valorTotal).reduce((sum, i) => sum + i, 0);
}

// Calcula o total pago, baseado na tabela
function totalPago(items) {
    return items.map(({ valorPago }) => valorPago).reduce((sum, i) => sum + i, 0);
}

// Para criar linhas
// const rows = [
//     createRow(1, 'Danilo', 'Bavaria lata', 1, 3.6, 3.6),
//     createRow(2, 'Luciano', 'Coca-cola lata', 2, 3.5),
//     createRow(3, 'Tiago', 'Caldinho', 1, 12)
// ];

const rowsComanda = [];
const rowsPagamento = [];

const jsonPagamento = {
    pagamentos:[
        {
            id: 1, 
            clienteSolicitante:{
                nome: 'Danilo de Nadai Sicari', 
                email: 'denadai.sicari@gmail.com'
            },
            valorPago: 9
        },
        {
            id: 2, 
            clienteSolicitante:{
                nome: 'Danilo de Nadai Sicari', 
                email: 'denadai.sicari@gmail.com'
            },
            valorPago: 9
        },
        {
            id: 3, 
            clienteSolicitante:{
                nome: 'Danilo de Nadai Sicari', 
                email: 'denadai.sicari@gmail.com'
            },
            valorPago: 9
        },
        {
            id: 4, 
            clienteSolicitante:{
                nome: 'Danilo de Nadai Sicari', 
                email: 'denadai.sicari@gmail.com'
            },
            valorPago: 9
        }
    ]
};


// exemplo de retorno json do servidor
const jsonComanda = {
    id: 1,
    estabelecimento: {
        id: 1,
        cnpj: 12345678901234,
        nome: 'Bar do zé',
        endereco: 'Rua da Santa Missa, 840, Centro, Leme/SP', 
        descricao: 'Desde 1978 servindo cerveja gelada e o melhor torresmo de Leme'
    },
    mesa: {
        id: 1,
        nome: 'Deck lago 3'
    },
    usuarios:[
        {nome: 'Erik Kenzo Oura Carlini Valle', email: 'erik@ciandt.com'}
    ],
    itemPedido:[
        {
            id: 1, 
            clienteSolicitante:{
                nome: 'Erik Kenzo Oura Carlini Valle', 
                email: 'erik@ciandt.com'
            },
            produto:{
                id: 2,
                nome: 'Bavaria',
                valor: 3,
                descricao: "A verdadeira puro malte, sangue de rodeio, super gelada",
                unidade: 'lata',
                categoria:{
                    id: 2,
                    categoria: 'Bebidas'
                }
            },
            observacao: 'copo sujo',
            quantidade: 3,
            valorUnitario: 3,
            valorTotal: 9
        },
        {
            id: 2, 
            clienteSolicitante:{
                nome: 'Erik Kenzo Oura Carlini Valle', 
                email: 'erik@ciandt.com'
            },
            produto:{
                id: 2,
                nome: 'Bavaria',
                valor: 3,
                descricao: "A verdadeira puro malte, sangue de rodeio, super gelada",
                unidade: 'lata',
                categoria:{
                    id: 2,
                    categoria: 'Bebidas'
                }
            },
            observacao: 'copo sujo',
            quantidade: 3,
            valorUnitario: 3,
            valorTotal: 9
        },
        {
            id: 3, 
            clienteSolicitante:{
                nome: 'Erik Kenzo Oura Carlini Valle', 
                email: 'erik@ciandt.com'
            },
            produto:{
                id: 2,
                nome: 'Bavaria',
                valor: 3,
                descricao: "A verdadeira puro malte, sangue de rodeio, super gelada",
                unidade: 'lata',
                categoria:{
                    id: 2,
                    categoria: 'Bebidas'
                }
            },
            observacao: 'copo sujo',
            quantidade: 3,
            valorUnitario: 3,
            valorTotal: 9
        },
        {
            id: 4, 
            clienteSolicitante:{
                nome: 'Erik Kenzo Oura Carlini Valle', 
                email: 'erik@ciandt.com'
            },
            produto:{
                id: 2,
                nome: 'Bavaria',
                valor: 3,
                descricao: "A verdadeira puro malte, sangue de rodeio, super gelada",
                unidade: 'lata',
                categoria:{
                    id: 2,
                    categoria: 'Bebidas'
                }
            },
            observacao: 'copo sujo',
            quantidade: 3,
            valorUnitario: 3,
            valorTotal: 9
        },
        {
            id: 5, 
            clienteSolicitante:{
                nome: 'Erik Kenzo Oura Carlini Valle', 
                email: 'erik@ciandt.com'
            },
            produto:{
                id: 2,
                nome: 'Bavaria',
                valor: 3,
                descricao: "A verdadeira puro malte, sangue de rodeio, super gelada",
                unidade: 'lata',
                categoria:{
                    id: 2,
                    categoria: 'Bebidas'
                }
            },
            observacao: 'copo sujo',
            quantidade: 3,
            valorUnitario: 3,
            valorTotal: 9
        }
    ],
    status: 'Comanda Aberta'
};

// Para cada itemPedido do json retornado do servidor, adicione em rows
// rows sera mapeada na tabela

jsonPagamento.pagamentos.forEach(item => {
    console.log(item);
    rowsPagamento.push(createRowPagamento(item.id, item.clienteSolicitante.nome, item.valorPago));
});

jsonComanda.itemPedido.forEach(item => {
    console.log(item);
    rowsComanda.push(createRow(item.id, item.clienteSolicitante.nome, item.produto.nome, item.quantidade, item.valorUnitario));
});

const invoiceSubtotal = subtotal(rowsComanda);
const invoiceTotal = invoiceSubtotal;

const invoiceTotalPago = totalPago(rowsPagamento);
const invoiceTotalPagar = invoiceTotal - invoiceTotalPago;



function ComponentePagamento(props) {

    const classes = useStyles();

    // Evento ao selecionar item no selectCliente
    const handleChangeSelectCliente = (event) => {
        console.log("Cliente selecionado=" + cliente)
        setCliente(event.target.value);
    };

    const [ cliente, setCliente ] = useState('');

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

                <Button variant="contained" color="primary" style={{background: '#009900', color: 'white'}}>
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

                                {rowsPagamento.map((row) => (
                                    <TableRow key={row.idPedido}>
                                        <TableCell width="50px">{row.cliente}</TableCell>
                                        <TableCell width="50px">{ccyFormat(row.valorPago)}</TableCell>
                                    </TableRow>
                                ))}

                                <TableRow>
                                    <TableCell width="200px"><b>Total Pago</b></TableCell>
                                    <TableCell >{ccyFormat(invoiceTotalPago)}</TableCell>
                                </TableRow>

                                <TableRow>
                                    <TableCell width="200px"><b>Total a Pagar</b></TableCell>
                                    <TableCell>{ccyFormat(invoiceTotalPagar)}</TableCell>
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
    
    // Evento ao selecionar item no selectCliente
    const handleChangeSelectCliente = (event) => {
        console.log("Cliente selecionado=" + cliente)
        setCliente(event.target.value);
    };

    const [ cliente, setCliente ] = useState('');

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

                                {rowsComanda.map((row) => (
                                    <TableRow key={row.idPedido}>
                                        <TableCell>{row.item}</TableCell>
                                        <TableCell align="right">{row.qtde}</TableCell>
                                        <TableCell align="right">{ccyFormat(row.valorUnitario)}</TableCell>
                                        <TableCell align="right">{ccyFormat(row.valorTotal)}</TableCell>
                                    </TableRow>
                                ))}

                                <TableRow>
                                    <TableCell colSpan={3}><b>Subtotal</b></TableCell>
                                    <TableCell align="right">{invoiceSubtotal}</TableCell>
                                </TableRow>

                                <TableRow>
                                    <TableCell colSpan={3}><b>Total Mesa</b></TableCell>
                                    <TableCell align="right">{invoiceTotal}</TableCell>
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