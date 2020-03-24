import React from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import IconButton from '@material-ui/core/IconButton';
import './style.css';

//icones
import AddCircleOutlineRoundedIcon from '@material-ui/icons/AddCircleOutlineRounded';
import RemoveCircleOutlineRoundedIcon from '@material-ui/icons/RemoveCircleOutlineRounded';
import RadioButtonUncheckedIcon from '@material-ui/icons/RadioButtonUnchecked';

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

function createData(item, precoUni, quant, precoTotal) {
  return { item, precoUni, quant, precoTotal};
}

const rows = [
  createData('Arroz com feijão', 12.00, 2, 24.00),
  createData('Batata frita', 20.00, 3, 60.00),
  createData('File Mignon', 36.50, 5, 183.25),
  createData('Sorvete do Luciano', 1000.00, 1, 1000.00),
];

export default function DenseTable() {
  const classes = useStyles();

  return (
    <div>
    <TableContainer component={Paper}>
      <Table className={classes.table} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow bgcolor="#1976d2">
            <TableCell class="ajustaTabela" align="center">Item</TableCell>
            <TableCell id="ajustaTabela" align="center">Preço Unitário</TableCell>
            <TableCell id="ajustaTabela" align="center">Quantidade</TableCell>
            <TableCell id="ajustaTabela" align="center">Total</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map(row => (
            <TableRow key={row.item}>
              <TableCell align="center" component="th" scope="row">
                {row.item}
              </TableCell>

              <TableCell align="center">{row.precoUni}</TableCell>

              <TableCell align="center">
               <IconButton>
                <RemoveCircleOutlineRoundedIcon />
               </IconButton> {row.quant}
                <IconButton>
                <AddCircleOutlineRoundedIcon />
                </IconButton>  </TableCell>

              <TableCell align="center">{(row.precoUni)*(row.quant)}&nbsp;<Button variant="outlined" color="primary">Obs.</Button></TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    
    <Button className="botaoMesmo" variant="contained" color="primary" width="100%" display="block"> Pedir </Button>
    <br></br>
    <Button className="botaoMesmo" variant="contained" color="secondary"> Cancelar </Button>
    </div>
  );
}
