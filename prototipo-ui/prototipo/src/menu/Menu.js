import React from 'react';
import ReactDOM from 'react-dom';
import { Button, Avatar } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';

export default function Menu(){
    return (
    <div class="wrapper">
        <div class="main_collumn">
            <h1>
                Estabelecimento X
            </h1>
            <div class="row_1">
            <span style={{fontSize: "25px"}}>Menu:</span>
            <Button size="small" className="btn_extrato" variant="contained" color="primary">Exibir Extrato</Button>
            </div>  
            <div>
                {<MenuList />}
            </div>
        </div>
        <aside class="lista_itens_wrapper">
                {<ItemList />}
        </aside>
    </div>
    )
}