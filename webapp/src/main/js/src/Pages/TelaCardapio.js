import React from 'react';
import { Button,
         FormHelperText,
         FormControl,
         InputLabel, 
         Select, 
         Badge,
         Paper,
         IconButton,
         Table,
         TableBody,
         TableCell,
         TableContainer,
         TableRow,
         Dialog,
         DialogActions,
         DialogContent,
         DialogContentText,
         DialogTitle,
         TextareaAutosize,
} from '@material-ui/core';

import api from "../Services/api";

//icones
import AddCircleOutlineRoundedIcon from '@material-ui/icons/AddCircleOutlineRounded';
import RemoveCircleOutlineRoundedIcon from '@material-ui/icons/RemoveCircleOutlineRounded';

import './TelaCardapio.css';

function createData(produto, observacao, quantidade, valorUnitario, valorTotal) {
    return { produto, observacao, quantidade, valorUnitario, valorTotal};
}

export default class Menu extends React.Component{
    constructor(props){
        super(props);
        this.state={
            carrinho: [],
            listaProduto: [],
            categorias: [],
            estabelecimento: {
                id: this.props.userLogin.idEstabelecimento,
                nome: null
            }
        };
    }

    checkUserInfo(){
        if(this.props.userLogin.comandaBlueCliente == ''){
            this.props.history.push("/login");
        }
        else{
            if(this.props.userLogin.idComanda == ''){
                this.props.history.push("/abrirComanda");
            }
        }
    }

    //Ao montar o componente faz-se a requisição do estabelecimento
    //E de todas as categorias do menu
    componentDidMount(){
        this.checkUserInfo();
        this.getCategoriasEstabelecimento();
        this.getMenu(-1);  
    }

    //limpa carrinho
    limparCarrinho(){
        this.setState({
            ...this.state,
            carrinho: []
        })
    }

    //Requisita o estabelecimento e suas categorias
    getCategoriasEstabelecimento(){
        const url = "/estabelecimento/"+this.state.estabelecimento.id+"/categorias"
        
        api.get(url)
        .then(response => {
            this.setState({
                ... this.state,
                categorias: response.data.categorias,
                estabelecimento: response.data.estabelecimento
            });  
        })
        .catch(error => {
            console.log(error);
        });
    }

    //Requisita o cardapio conforme um idCategoria e adiciona no state
    //caso o idCategoria for -1 é resgatado todas as categorias
    getMenu(idCategoria){
        const url = (idCategoria==-1)?
        "/estabelecimento/"+this.state.estabelecimento.id+"/cardapio/produtos":
        "/estabelecimento/"+this.state.estabelecimento.id+"/cardapio/produtos/categoria/"+idCategoria;

        api.get(url)
        .then(response => {
            this.setState({
                ...this.state,
                listaProduto: response.data
            })
        })
        .catch(error => {
            console.log(error);
        });
    }

    //TODO
    //Posta na api os pedidos do referido carrinho
    postCarrinho(){
        const url = "/estabelecimento/"+this.state.estabelecimento.id+
                    "/mesas/"+this.props.userLogin.idMesa+
                    "/comandas/"+this.props.userLogin.idComanda+
                    "/pedir";
        
        const data = this.state.carrinho.slice();

        const options = {
            headers: {'COMANDA-BLUE-CLIENTE': this.props.userLogin.comandaBlueCliente}
        };

        api.post(url, data, options)
        .then( response => {
            console.log(response);
            this.limparCarrinho();
        }).catch(error =>{
            console.log(error);
        });
    }

    //From: <Select label="Categoria">
    //requisita o cardapio novamente conforme a categoria especificada
    //pelo ComboBox
    handleChange = (event) => {
        this.getMenu(event.target.value);
    }

    //From: <MenuList>
    //adiciona um pedido no state carrinho
    handleAddItem = (item) =>{
        let newCarrinho = this.state.carrinho.slice();
        newCarrinho.push(createData(item, "", 1, item.valor, 1*item.valor))
        this.setState({
            ...this.state,
            carrinho: newCarrinho
        })
    }

    //From: <ItemList>
    //incrementa a quantidade de um pedido em 1
    handleIncrementItem = (i) =>{
        const rows = this.state.carrinho.slice();
        rows[i].quantidade++;
        rows[i].valorTotal = rows[i].quantidade*rows[i].valorUnitario;
        
        this.setState({
            ...this.state,
            carrinho: rows
        });
    }
    
    //From: <ItemList>
    //decrementa a quantidade de um pedido em 1
    //caso a quantidade se igualar a 0 o pedido é deletado
    handleDecrementItem = (i) =>{
        const rows = this.state.carrinho.slice();
        rows[i].quantidade--;
        if(rows[i].quantidade==0){
            rows.splice(i, 1);
        }
        else{
            rows[i].valorTotal = rows[i].quantidade*rows[i].valorUnitario;
        }

        this.setState({
            ...this.state,
            carrinho: rows
        });
    }

    //From: <ItemList>
    //acrescenta uma observação ao pedido carrinho[i]
    handleObsSend = (obs, i) =>{
        this.state.carrinho[i].observacao = obs;
        
        this.setState({
            ...this.state,
            carrinho: this.state.carrinho
        });
    }

    //From: <ItemList>
    //Posta no banco os pedidos do referido carrinho
    //limpa o carrinho
    handleCarrinhoPedir = () =>{
        this.postCarrinho();
    }

    //From: <ItemList>
    //Cancela o carrinho: limpa ele
    handleCancelCarrinho = () =>{
        this.limparCarrinho();
    }

    render(){
        return (
            <div class="wrapper">
                <div class="main_collumn">
                    <h1 class="estabelecimento_title">
                        {this.state.estabelecimento.nome}
                    </h1>
                    <div class="row_1">
                    
                    <FormControl variant="outlined" className="ComboBox">
                        <InputLabel>Categorias</InputLabel>
                        <Select native label="Categoria"
                        value={this.state.idCategoriaAtual}
                        onChange={this.handleChange}>
                            <option aria-label="None" value={-1}>Todas</option>
                            {this.state.categorias.map((item, key) => {
                                return(
                                <option value={item.id}>{item.categoria}</option>
                                )
                            })}
                        </Select>
                        <FormHelperText>Selecione uma categoria</FormHelperText>
                    </FormControl>
                    
                    <Button size="small" className="btn_extrato" variant="contained" color="primary">Exibir Extrato</Button>
                    </div>  
                    <MenuList OnAddItem={this.handleAddItem} 
                      listaProduto={this.state.listaProduto}
                    /> 
                    
                </div>
                <aside class="lista_itens_wrapper">
                    <ItemsList OnIncrementItem={this.handleIncrementItem}
                              OnDecrementItem={this.handleDecrementItem}
                              OnObsSend={this.handleObsSend}
                              carrinho={this.state.carrinho} 
                              OnCarrinhoPedir={this.handleCarrinhoPedir}
                              OnCancelCarrinho={this.handleCancelCarrinho}
                    />
                </aside>
            </div>
        );
    }
}

function Categoria(props){
    return(
        <>             
        <h1 className="product-categoria">
            {props.categoria}
        </h1>
        <ul id={props.id}>
            {props.produtos.map((item, key)=>{
                const handleClick = () =>{
                    props.OnItemClick(item);
                }

                return(
                <li className="line">
                    <img className="product-image" src={item.imagemDoProduto}/>
                    <button onClick={handleClick} className="product-button">+</button>

                    <h1 className="product-title">{item.nome}</h1>
                    <p className="product-descricao">{item.descricao}</p>
                    <h2 className="product-valor">R${item.valor.toFixed(2)}</h2>
                </li>
                );
            })}
        
        </ul>
        </>
    );
}

class MenuList extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        const produtos=this.props.listaProduto.slice();
        
        produtos.sort(
            (a,b) => (a.categoriaProduto.id > b.categoriaProduto.id) ? 1:-1
        );

        let idCategoriaAtual = -1;
        let categorias = [];
        let produtosDaCategoria;
        let i = -1;

        produtos.forEach((item, key) => {
            if(item.categoriaProduto.id != idCategoriaAtual){
                idCategoriaAtual = item.categoriaProduto.id;
                produtosDaCategoria = [item];
                categorias.push(
                    <Categoria id={item.categoriaProduto.id}
                               categoria={item.categoriaProduto.categoria}
                               produtos={produtosDaCategoria}
                               OnItemClick={(produto) =>{this.props.OnAddItem(produto)}}
                    />
                );
                i++;
            }
            else{
                produtosDaCategoria.push(item);
                categorias[i] = <Categoria id={item.categoriaProduto.id}
                                           categoria={item.categoriaProduto.categoria}
                                           produtos={produtosDaCategoria}
                                           OnItemClick={(produto) =>{this.props.OnAddItem(produto)}}
                                />;
            }
        });

        return (
            <div className="container">
                <div className="MenuList">
                    {categorias.length===0?
                        <span>Não há itens para serem listados</span>:
                        categorias
                    }
                </div>
            </div>
        );
    }    
}

class ItemsList extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            obsAtual: "",
            iPedidoAtual: null
        }
    }

    render(){
        //Emite ao componente pai que o carrinho
        //esta sendo pedido
        const handleClickPedir = ()=>{
            this.props.OnCarrinhoPedir();
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
                                        {row.observacao != ""?
                                        <Badge badgeContent={"!"} color="primary">
                                            <Button variant="outlined" color="primary" className="gold" onClick={()=>handleClickOpen(key)}> OBS </Button>
                                        </Badge>:
                                        <Button variant="outlined" color="primary" className="gold" onClick={()=>handleClickOpen(key)}> OBS </Button>
                                        }
                                        
                                    </TableCell>
                                
                                </TableRow>
                            )})}
                        </TableBody>
                    </Table>
                </TableContainer>
                
                <div className="buttonBox">
                    <Button className="botaoMesmo" variant="contained" color="primary" width="100%" display="block" onClick={handleClickPedir}> Pedir </Button>
                    <br></br>
                    <Button onClick={() => this.props.OnCancelCarrinho()}className="botaoMesmo" variant="contained" color="secondary"> Cancelar </Button>
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