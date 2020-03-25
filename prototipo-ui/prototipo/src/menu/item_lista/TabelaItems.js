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
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';

import './style.css';

//icones
import AddCircleOutlineRoundedIcon from '@material-ui/icons/AddCircleOutlineRounded';
import RemoveCircleOutlineRoundedIcon from '@material-ui/icons/RemoveCircleOutlineRounded';
import RadioButtonUncheckedIcon from '@material-ui/icons/RadioButtonUnchecked';

const useStyles = makeStyles({
  table: {
    minWidth: "1cm",
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
  createData('Arroz com feijão', 12.00, 2, 24.00),
  createData('Batata frita', 20.00, 3, 60.00),
  createData('File Mignon', 36.50, 5, 183.25),
  createData('Sorvete do Luciano', 1000.00, 1, 1000.00),
  createData('Arroz com feijão', 12.00, 2, 24.00),
  createData('Batata frita', 20.00, 3, 60.00),
  createData('File Mignon', 36.50, 5, 183.25),
  createData('Sorvete do Luciano', 1000.00, 1, 1000.00),
  createData('Arroz com feijão', 12.00, 2, 24.00),
  createData('Batata frita', 20.00, 3, 60.00),
  createData('File Mignon', 36.50, 5, 183.25),
  createData('Sorvete do Luciano', 1000.00, 1, 1000.00),
];

export default function DenseTable() {
  const classes = useStyles();

  const [open, setOpen] = React.useState(false);

  const handleKeyPress = event =>{
    if(event.key === 'Enter'){
        handleClose();
    }
  }

  const handleChange = (e) => {
    const nick = e.target.value.trim();
    console.log(nick);
  }

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  
  function subtrai(quant) {
    quant--;
  }

  function soma(quant) {
    quant++;
    return Boolean(true);
  }

  return (
    <div className="itens-border">
      <div className="itensTitle">
        <h1>ITENS</h1>
      </div>
      <TableContainer className="content" component={Paper}>
      <Table size="small" aria-label="a dense table">
        <TableBody>
          {rows.map(row => (
            <TableRow className="tableRow" key={row.item}>
              <TableCell className="ajustaTabela" component="th" scope="row">
                {row.item}
              </TableCell>

              <TableCell className="ajustaTabela">
                R$ {row.precoUni}
              </TableCell>

              <TableCell className="ajustaTabela">
               <IconButton className="iconButton" onClick={() => { subtrai(row.quant); }}>
                <RemoveCircleOutlineRoundedIcon className="vermelho"/>
               </IconButton> 
                  {subtrai(row.quant) ? --row.quant : ++row.quant}
                <IconButton className="iconButton">
                <AddCircleOutlineRoundedIcon className="verde"/>
                </IconButton>  </TableCell>
                
                <TableCell className="ajustaTabela">
                  <Button variant="outlined" color="primary" className="gold" onClick={handleClickOpen}> OBS </Button>
                </TableCell>
              
            </TableRow>))}
        </TableBody>
      </Table>
    </TableContainer>
    
      <div className="buttonBox">
        <Button className="botaoMesmo" variant="contained" color="primary" width="100%" display="block"> Pedir </Button>
        <br></br>
        <Button className="botaoMesmo" variant="contained" color="secondary"> Cancelar </Button>
      </div>

    <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Observação</DialogTitle>
        <DialogContent>
          <DialogContentText>
           Insira sua observação, se deseja retirar alguma coisa, ponto da carne, ...
          </DialogContentText>
          <TextareaAutosize
                style={{width: "100%"}}
                rowsMin={5}
                rowsMax={5}
                aria-label="Observações"
                placeholder="Digite suas observações..."
                onKeyPress={handleKeyPress} onChange={handleChange}
            />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancelar
          </Button>
          <Button onClick={handleClose} color="primary">
            Enviar
          </Button>
        </DialogActions>
      </Dialog>

    </div>
  );
}
