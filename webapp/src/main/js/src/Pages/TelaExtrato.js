import React, {useState } from 'react';
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

import '../style.css';

const useStyles = makeStyles({
  table: {
    minWidth: 100,
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

// Cria linha baseado nos parametros fornecidos
function createRow(idPedido, cliente, item, qtde, valorUnitario) {
    const valorTotal = priceRow(valorUnitario, qtde);
    return { idPedido, cliente, item, qtde, valorUnitario, valorTotal };
}

// Calcula o total, baseado na tabela
// TODO entender melhor o reduce
function subtotal(items) {
    return items.map(({ valorTotal }) => valorTotal).reduce((sum, i) => sum + i, 0);
}

// Para criar linhas
// const rows = [
//     createRow(1, 'Danilo', 'Bavaria lata', 1, 3.6, 3.6),
//     createRow(2, 'Luciano', 'Coca-cola lata', 2, 3.5),
//     createRow(3, 'Tiago', 'Caldinho', 1, 12)
// ];

const rows = [];

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
        {nome: 'Danilo de Nadai Sicari', email: 'denadai.sicari@gmail.com'},
        {nome: 'Erik Kenzo Oura Carlini Valle', email: 'erik@ciandt.com'}
    ],
    itemPedido:[
        {
            id: 1, 
            clienteSolicitante:{
                nome: 'Danilo de Nadai Sicari', 
                email: 'denadai.sicari@gmail.com'
            },
            produto:{
                id: 1,
                nome: 'Torresmo frito',
                valor: 12.50,
                descricao: "Torresmo frito no óleo, acompanha molho especial",
                unidade: 'unidade',
                categoria:{
                    id: 1,
                    categoria: 'Salgados'
                }
            },
            observacao: 'capricha na capa da gordura',
            quantidade: 2,
            valorUnitario: 12.5,
            valorTotal: 25.0
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
        }
    ],
    status: 'Comanda Aberta'
};

// Para cada itemPedido do json retornado do servidor, adicione em rows
// rows sera mapeada na tabela
jsonComanda.itemPedido.forEach(item => {
    console.log(item);
    rows.push(createRow(item.id, item.clienteSolicitante.nome, item.produto.nome, item.quantidade, item.valorUnitario));
});

const invoiceSubtotal = subtotal(rows);
const invoiceTotal = invoiceSubtotal;

export default function TelaExtrato(props) {

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
            container style={{ height: "10%" }}
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
                <h1>EXTRATO</h1>
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
                direction="column" item xs={12} sm={6}>
                <Button variant="contained" color="primary" style={{background: '#2d9bf0', color: 'white'}}
                >
                    Voltar ao menu
                </Button>
            </Grid>

            <Grid
                container
                spacing={0}
                justify="center"
                direction="column" item xs={12} sm={6}
            >

                <FormControl variant="outlined" className={classes.formControl}>
                    <InputLabel id="labelSelectCliente">Cliente</InputLabel>
                    <Select
                        labelId="labelSelectCliente"
                        id="selectCliente"
                        value={cliente}
                        onChange={handleChangeSelectCliente}
                        label="Cliente"
                    >
                        <MenuItem key="all" value="all"><em>Todos</em></MenuItem>
                        
                        {jsonComanda.usuarios.map((cliente) => (
                            <MenuItem key={cliente.email} value={cliente.email}>{cliente.nome}</MenuItem>
                        ))}

                    </Select>
                </FormControl>
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
                direction="column" item xs={11} sm={11} lg={8} id="tabelaExtrato"
            >

                <TableContainer fixedHeader ={true}>
                    <Table className={classes.table} aria-label="tabela extrato">

                        <TableHead >
                            <TableRow >
                                <TableCell><b>Cliente</b></TableCell>
                                <TableCell><b>Item</b></TableCell>
                                <TableCell align="right"><b>Quantidade</b></TableCell>
                                <TableCell align="right"><b>Valor Unitário (R$)</b></TableCell>
                                <TableCell align="right"><b>Valor Total (R$)</b></TableCell>
                            </TableRow>
                        </TableHead>

                        <TableBody>

                            {rows.map((row) => (
                                <TableRow key={row.idPedido}>
                                    <TableCell>{row.cliente}</TableCell>
                                    <TableCell>{row.item}</TableCell>
                                    <TableCell align="right">{row.qtde}</TableCell>
                                    <TableCell align="right">{ccyFormat(row.valorUnitario)}</TableCell>
                                    <TableCell align="right">{ccyFormat(row.valorTotal)}</TableCell>
                                </TableRow>
                            ))}

                            <TableRow>
                                <TableCell colSpan={4}><b>Subtotal</b></TableCell>
                                <TableCell align="right">{ccyFormat(invoiceSubtotal)}</TableCell>
                            </TableRow>

                            <TableRow>
                                <TableCell colSpan={4}><b>Total Mesa</b></TableCell>
                                <TableCell align="right">{ccyFormat(invoiceTotal)}</TableCell>
                            </TableRow>

                        </TableBody>

                    </Table>
                </TableContainer>

            </Grid>   

        </Grid>
        
        <Grid
        container
        style={{ height: "87%" }}
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
            direction="column" item xs={11} sm={11} lg={8} id="tabelaExtrato"
            style={{padding: '20px',}}
            >
                <Button 
                variant="contained" 
                style={{ background: '#2d9bf0', color: 'white', height: '40px', width:'100px',}}
                >
                    Pagar
                </Button>

            </Grid>
        </Grid>


    </Grid>
  );
}