import React, { useState, useEffect } from 'react';
import { Button, Avatar, FormHelperText, FormControl, InputLabel, Select, NativeSelect } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';
import api from "../../services/api";

export default function Menu(){
    
    const [state, setState] = React.useState({
        carrinho: [],
        categorias: [],
        estabelecimento: {
            id: 1,
            nome: "Bar do zé"
        }
    });

    useEffect(()=> {
        const url = "/estabelecimento/"+state.estabelecimento.id+"/categorias"
        
        api.get(url)
        .then(response => {
            this.setState({
                ... state,
                categorias: response.data.categorias,
                estabelecimento: response.data.estabelecimento
            });  
        })
        .catch(error => {
            console.log(error);
        });
    });

    //todo
    const handleChange = event => {
        const idCategoriaAtual = event.target.idCategoriaAtual;
        setState({
          ...state,
          [idCategoriaAtual]: event.target.value,
        });
    };

    //adicione produto no state carrinho
    const handleAddItem = (produto) =>{
        let newCarrinho = state.carrinho.slice();
        newCarrinho.push(produto)
        setState({
            ... state,
            carrinho: newCarrinho
        })
        
    }

    return (
        <div class="wrapper">
            <div class="main_collumn">
                <h1 class="estabelecimento_title">
                    {state.estabelecimento.nome}
                </h1>
                <div class="row_1">
                
                <FormControl variant="outlined" className="ComboBox">
                    <InputLabel>Categorias</InputLabel>
                    <Select native label="Categoria"
                    value={state.idCategoriaAtual}
                    onChange={handleChange}>
                        <option aria-label="None" value="" />
                        {state.categorias.map((item, key) => {
                            return(
                            <option value={item.id}>{item.categoria}</option>
                            )
                        })}
                    </Select>
                    <FormHelperText>Selecione uma categoria</FormHelperText>
                </FormControl>
                

                <Button size="small" className="btn_extrato" variant="contained" color="primary">Exibir Extrato</Button>
                </div>  
                <MenuList OnAddItem={handleAddItem} idCategoria={-1} idEstabelecimento={1}/> 
                
            </div>
            <aside class="lista_itens_wrapper">
                    {<ItemList carrinho={state.carrinho} />}
            </aside>
        </div>
    );
}