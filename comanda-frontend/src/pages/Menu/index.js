import React, { useState, useEffect } from 'react';
import { Button, Avatar, FormHelperText, FormControl, InputLabel, Select, NativeSelect } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';
import api from "../../services/api";

export default class Menu extends React.Component{
    constructor(props){
        super(props);
        this.state={
            carrinho: [],
            categorias: [],
            estabelecimento: {
                id: 1,
                nome: null
            },
            idCategoriaAtual: -1,
        };
    }


    //Entra em loop componentDidMount componentDidUpdate
    componentDidMount(){
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

    handleChange = event => {
        this.setState({
          ...this.state,
          idCategoriaAtual: event.target.value,
        });
    }

    //adicione produto no state carrinho
    handleAddItem = (produto) =>{
        let newCarrinho = this.state.carrinho.slice();
        newCarrinho.push(produto)
        this.setState({
            ...this.state,
            carrinho: newCarrinho
        })
               
    }

    renderMenuList(){
        return(
            <MenuList OnAddItem={this.handleAddItem} 
                      idCategoria={this.state.idCategoriaAtual}
                      idEstabelecimento={this.state.estabelecimento.id}
            />
        )
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
                            <option aria-label="None" value={-1} />
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
                        {<ItemList carrinho={this.state.carrinho} />}
                </aside>
            </div>
        );
    }
}