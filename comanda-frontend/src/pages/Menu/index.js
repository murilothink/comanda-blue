import React from 'react';
import { Button, Avatar } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';

export default function Menu(){
    const [state, setState] = React.useState({
        carrinho: []
    });

    //adicione produto no state carrinho
    const handleAddItem = (produto) =>{
        //todo
        console.log(produto);
    }

    return (
        <div class="wrapper">
            <div class="main_collumn">
                <h1 class="estabelecimento_title">
                    Estabelecimento X
                </h1>
                <div class="row_1">
                <span style={{fontSize: "25px"}}>Menu:</span>
                <Button size="small" className="btn_extrato" variant="contained" color="primary">Exibir Extrato</Button>
                </div>  
                <MenuList OnAddItem={handleAddItem} idCategoria={-1} idEstabelecimento={1}/> 
                
            </div>
            <aside class="lista_itens_wrapper">
                    {<ItemList />}
            </aside>
        </div>
    );
}
