import React, { useState, useEffect } from 'react';
import { Button, Avatar, FormHelperText, FormControl, InputLabel, Select, NativeSelect } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';
import api from "../../services/api";

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
                id: 1,
                nome: null
            },
            mesaId: 1
        };
    }

    //Ao montar o componente faz-se a requisição do estabelecimento
    //E de todas as categorias do menu
    componentDidMount(){
        this.getCategoriasEstabelecimento();
        this.getMenu(-1);    
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
                    <ItemList OnIncrementItem={this.handleIncrementItem}
                              OnDecrementItem={this.handleDecrementItem}
                              OnObsSend={this.handleObsSend}
                              carrinho={this.state.carrinho} 
                              mesaId={this.state.mesaId}
                    />
                </aside>
            </div>
        );
    }
}