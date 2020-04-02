import React, { useState, useEffect } from 'react';
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
    return `R$ ${num.toFixed(2)}`;
}

// Calcula valor total linha
function priceRow(qtde, valorUnitario) {
    return qtde * valorUnitario;
}

// Cria linha baseado nos parametros fornecidos
function createRow(idPedido, cliente, email, item, qtde, valorUnitario) {
    const valorTotal = priceRow(valorUnitario, qtde);
    return { idPedido, cliente, email, item, qtde, valorUnitario, valorTotal };
}

export default function TelaExtrato(props) {

    console.log("TelaExtrato, userLogin", props.userLogin);

    const [rows, setRows] = useState([]);

    // Ao carregar tela, puxar pedidos da comanda->mesa->estabelecimento
    useEffect(() => {
        async function loadPedidos() {
            try {
                const options = {
                    headers: { 'COMANDA-BLUE-CLIENTE': props.userLogin.comandaBlueCliente }
                };

                const response = await api.get(`/estabelecimento/${props.userLogin.idEstabelecimento}/mesas/${props.userLogin.idMesa}/comandas/${props.userLogin.idComanda}/pedidos`);

                response.data.forEach(item => {
                    //rows.push(createRow(item.id, item.clienteSolicitante.nome, item.clienteSolicitante.email, item.produto.nome, item.quantidade, item.valorUnitario));
                    setRows(oldRows => [...oldRows, createRow(item.id, item.clienteSolicitante.nome, item.clienteSolicitante.email, item.produto.nome, item.quantidade, item.valorUnitario)]);
                });

                //console.log(rows);
            }
            catch (error) {
                console.log(error);
            }
        }

        loadPedidos();
    }, []);

    const classes = useStyles();

    // Evento ao selecionar item no selectCliente
    const handleChangeSelectCliente = (event) => {
        console.log("Cliente selecionado=" + cliente)
        setCliente(event.target.value);
    };

    const [cliente, setCliente] = useState('all');

    const subtotal = (items) => {
        let somaSubTotal = 0.0;
        items.forEach(element => (element.email == cliente) ? somaSubTotal += parseFloat(element.valorTotal) : (cliente == "all") ? somaSubTotal += parseFloat(element.valorTotal) : null);
        return somaSubTotal;
    }

    const total = (items) => {
        let somaTotal = 0.0;
        items.forEach(element => somaTotal += parseFloat(element.valorTotal));
        return somaTotal
    }

    //criaçao de lista com nomes e emails dos usuarios que participam da comanda 
    const listaNomes = Array.from(new Set(rows.map(usuario => usuario.email)))
        .map(email => {
            return {
                email: email,
                nome: rows.find(usuario => usuario.email === email).cliente
            };
        });

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
                    <Button variant="contained" color="primary" style={{ background: '#2d9bf0', color: 'white' }}
                        onClick={() => props.history.push('/menu')}
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

                            {listaNomes.map((clientes) => (
                                <MenuItem key={clientes.email} value={clientes.email}>{clientes.nome}</MenuItem>
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

                    <TableContainer>
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

                                {rows.map((row) => {
                                    return ((cliente == row.email) ?
                                        <TableRow key={row.idPedido}>
                                            <TableCell>{row.cliente}</TableCell>
                                            <TableCell>{row.item}</TableCell>
                                            <TableCell align="right">{row.qtde}</TableCell>
                                            <TableCell align="right">{ccyFormat(row.valorUnitario)}</TableCell>
                                            <TableCell align="right">{ccyFormat(row.valorTotal)}</TableCell>
                                        </TableRow> :
                                        (cliente == "all") ?
                                            <TableRow key={row.idPedido}>
                                                <TableCell>{row.cliente}</TableCell>
                                                <TableCell>{row.item}</TableCell>
                                                <TableCell align="right">{row.qtde}</TableCell>
                                                <TableCell align="right">{ccyFormat(row.valorUnitario)}</TableCell>
                                                <TableCell align="right">{ccyFormat(row.valorTotal)}</TableCell>
                                            </TableRow> : null
                                    )
                                })}

                                <TableRow>
                                    <TableCell colSpan={4}><b>Subtotal</b></TableCell>
                                    <TableCell align="right">{ccyFormat(subtotal(rows))}</TableCell>
                                </TableRow>

                                <TableRow>
                                    <TableCell colSpan={4}><b>Total Mesa</b></TableCell>
                                    <TableCell align="right">{ccyFormat(total(rows))}</TableCell>
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
                    style={{ padding: '20px', }}
                >
                    <Button
                        variant="contained"
                        style={{ background: '#2d9bf0', color: 'white', height: '40px', width: '100px', }}
                    >
                        Pagar
                </Button>

                </Grid>
            </Grid>


        </Grid>
    );
}