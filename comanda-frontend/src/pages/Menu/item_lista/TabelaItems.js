import React, {useEffect} from 'react';
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import IconButton from '@material-ui/core/IconButton';
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

export default class TabelaItems extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            obsAtual: "",
            iPedidoAtual: null
        }
    }

    render(){
        //TODO
        //Envia ao banco os pedidos do carrinho e esvazia o carrinho
        const handleClickPedir = ()=>{
            console.log("Pedindo carrinho... Bibiiii")
    
            console.log(this.props.carrinho)
        }

        //From: <Button>OBS</Button>
        //Seta a observacao a ser modificada e
        //o index do seu respectivo pedido no carrinho.
        //Abre o modal
        const handleClickOpen = (key) => {
            this.setState({
                ...this.state,
                obsAtual: this.props.carrinho[key].observacao
                , iPedidoAtual:key
            });
        };

        //From: <TextareaAutoSize> / do modal
        //Caso a tecla pressionada for ENTER fecha o modal
        const handleKeyPress = event =>{
            if(event.key === 'Enter'){
                handleClose();
            }
        }

        //From: <TextareaAutoSize> / do modal
        //modifica o estado que representa a observacao a ser modificada
        //quando existe uma mudanca no textarea
        const handleChange = (e) => {
            const obs = e.target.value;
            this.setState({
                ...this.state,
                obsAtual: obs,
            });
        }

        //From: <Button>Cancelar</Button> / do modal
        //limpa os estados referentes a modificacao de observacao
        //e fecha o modal
        const handleClose = () => {
            this.setState({
                ...this.state,
                obsAtual: "",
                iPedidoAtual: null
            });
        };

        //From: <Button>Enviar</Button> / do modal
        //emite, ao componente pai, o evento que envia a observacao
        //que foi modificada e seu respectivo index no carrinho
        const handleObsEnviar = () =>{
            this.props.OnObsSend(this.state.obsAtual, this.state.iPedidoAtual);
            handleClose();
        }

        return (
            <div className="itens-border">

                <div className="itensTitle">
                    <h1>ITENS</h1>
                </div>

                <TableContainer className="content" component={Paper}>
                    <Table size="small" aria-label="a dense table">
                        <TableBody>
                            {this.props.carrinho.map((row, key) => {return(
                                <TableRow className="tableRow" key={key}>
                                    
                                    <TableCell className="ajustaTabela" component="th" scope="row">
                                        {row.produto.nome}
                                    </TableCell>

                                    <TableCell className="ajustaTabela">
                                        R$ {row.valorUnitario}
                                    </TableCell>

                                    <TableCell className="ajustaTabela">

                                        <IconButton className="iconButton">
                                            <RemoveCircleOutlineRoundedIcon onClick={()=>this.props.OnDecrementItem(key)} className="vermelho"/>
                                        </IconButton> 
                                        
                                        {row.quantidade}

                                        <IconButton className="iconButton">
                                            <AddCircleOutlineRoundedIcon onClick={()=>this.props.OnIncrementItem(key)} className="verde"/>
                                        </IconButton> 

                                    </TableCell>
                                    
                                    <TableCell className="ajustaTabela">
                                        <Button variant="outlined" color="primary" className="gold" onClick={()=>handleClickOpen(key)}> OBS </Button>
                                    </TableCell>
                                
                                </TableRow>
                            )})}
                        </TableBody>
                    </Table>
                </TableContainer>
                
                <div className="buttonBox">
                    <Button className="botaoMesmo" variant="contained" color="primary" width="100%" display="block" onClick={handleClickPedir}> Pedir </Button>
                    <br></br>
                    <Button className="botaoMesmo" variant="contained" color="secondary"> Cancelar </Button>
                </div>

                <Dialog open={this.state.iPedidoAtual!=null} onClose={handleClose} aria-labelledby="form-dialog-title">
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
                            value={this.state.obsAtual}
                        />
                    </DialogContent>
                    <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancelar
                    </Button>
                    <Button onClick={handleObsEnviar} color="primary">
                        Enviar
                    </Button>
                    </DialogActions>
                </Dialog>

            </div>
        );
    }
}
