import React, { useState, useEffect } from 'react';
import { Button, Avatar, FormHelperText, FormControl, InputLabel, Select, NativeSelect } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';
import api from "../../services/api";

function createData(item, precoUni, quant, precoTotal) {
    return { item, precoUni, quant, precoTotal};
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
            }
        };
    }

    componentDidMount(){
        this.getCategoriasEstabelecimento();

        this.getMenu(-1);    
    }

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

    handleChange = event => {
        this.getMenu(event.target.value);
    }


    //adicione produto no state carrinho
    handleAddItem = (item) =>{
        let newCarrinho = this.state.carrinho.slice();
        newCarrinho.push(createData(item.nome, item.valor, 1, 1*item.valor))
        this.setState({
            ...this.state,
            carrinho: newCarrinho
        })
    }

    handleIncrementItem = (i) =>{
        console.log(i);
        const rows = this.state.carrinho.slice();
        rows[i].quant++;
        rows[i].precoTotal = rows[i].quant*rows[i].precoUni;
        console.log(rows)
        
        this.setState({
            ...this.state,
            carrinho: rows
        });
    }
    
    handleDecrementItem = (i) =>{
        const rows = this.state.carrinho.slice();
        rows[i].quant--;
        if(rows[i].quant==0){
            rows.splice(i, 1);
        }
        else{
            rows[i].precoTotal = rows[i].quant*rows[i].precoUni;
        }

        this.setState({
            ...this.state,
            carrinho: rows
        });
    }

    renderMenuList(){
        return(
            <MenuList OnAddItem={this.handleAddItem} 
                      listaProduto={this.state.listaProduto}
            />
        )
    }

    renderItemList(){
        return(
            <ItemList OnIncrementItem={this.handleIncrementItem}
                      OnDecrementItem={this.handleDecrementItem}
                      carrinho={this.state.carrinho} 
            />
        )
    }

    render(){
        console.log("teste");
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
                    {this.renderMenuList()} 
                    
                </div>
                <aside class="lista_itens_wrapper">
                    {this.renderItemList()}
                </aside>
            </div>
        );
    }
}