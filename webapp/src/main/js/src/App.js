import React, { useState, useMemo } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { createContext } from "react";


// assets
import './style.css';
import imageLogo from './static/img/CI&T.png'

import Toolbar from '@material-ui/core/Toolbar';
import AppBar from '@material-ui/core/AppBar';


import TelaLogin from './Pages/TelaLogin';
import TelaAbrirComanda from './Pages/TelaAbrirComanda';

// Criar contexto para login do usuario em memoria REACT
const UserContext = createContext(null);

function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={(props) => <TelaLogin {...props} userContext={UserContext} />} />
                <Route path="/abrircomanda" exact component={(props) => <TelaAbrirComanda {...props} userContext={UserContext} />} />                
            </Switch>
        </BrowserRouter>
    );
}

class ComandaHeader extends React.Component{
    render() {
        return(            
          <div className="header">     
            <AppBar position="static" style={{ background: '#2d9bf0'}}>
              <Toolbar style={{ height: '60px'}}>
                <img src={imageLogo} alt="Logo cit" className="logo-cit" />
                <h2 className="textheader">ComandaBlue</h2>
              </Toolbar>
            </AppBar>
          </div>
        )
    }
}

class ComandaFooter extends React.Component {
    render(){
        return(
            <footer className="footer">
                <h1 className="textfooter">Estags GU BLUE</h1>
                <h3 className="textcopy">Copyright © Comanda - BLUE 2020.</h3>
            </footer>
        )
    }
}

export default function App(){

    // Estados a serem guardados para login usuario
    const [userLogin, setUserLogin] = useState({
        nome: '',
        comandaBlueCliente: '',
        idEstabelecimento: '',
        idMesa: ''       
    }); 

    // Toda vez que userLogin mudar, atualiza providerUserLogin 
    const providerUserLogin = useMemo(() => ({userLogin, setUserLogin}), [userLogin, setUserLogin]);

    // envolver <Routes /> com UserContext para utilizarmos como forma de login em memoria REACT
    return(
        <div className="Comanda">
            <ComandaHeader />
            <div className="content">
                <UserContext.Provider value={providerUserLogin}>
                    <Routes />
                </UserContext.Provider>
            </div>
            <ComandaFooter />
        </div>
    );
}