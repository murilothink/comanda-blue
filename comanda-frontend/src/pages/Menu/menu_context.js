import React, { useContext } from 'react';
import { UserContext } from '../../UserContext';
import { Button, Avatar } from '@material-ui/core';
import "./style.css";
import MenuList from './menu_lista/MenuList';
import ItemList from './item_lista/TabelaItems';

// ARQUIVO PARA ORIENTACAO DE COMO FAZER LOGIN!!!

export default function Menu({history}){

    // Para acessar contexto de login usuario
    const { userLogin, setUserLogin } = useContext(UserContext);
 
    return (            
        <div class="wrapper">
            <div class="main_collumn">
                <h1 class="estabelecimento_title">
                    Estabelecimento X, id={userLogin.idEstabelecimento}
                </h1>
                <div class="row_1">
                <span style={{fontSize: "25px"}}>Menu:</span>
                <Button size="small" className="btn_extrato" variant="contained" color="primary">Exibir Extrato</Button>
                </div>  
                <MenuList idCategoria={-1} idEstabelecimento={1}/> 
                
            </div>
            <aside class="lista_itens_wrapper">
                    {<ItemList />}
            </aside>
        </div>
    )
}
