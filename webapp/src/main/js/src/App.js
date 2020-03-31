import React, { useState, useMemo } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

// assets
import './style.css';
import imageLogo from './static/img/CI&T.png'

import Toolbar from '@material-ui/core/Toolbar';
import AppBar from '@material-ui/core/AppBar';


import TelaLogin from './Pages/TelaLogin';
import TelaAbrirComanda from './Pages/TelaAbrirComanda';
import TelaScanQrcode from './Pages/TelaScanQrcode';
import TelaExtrato from './Pages/TelaExtrato';

function Routes(){
    // Estados a serem guardados para login usuario
    const [userLogin, setUserLogin] = useState({
        nome: '',
        comandaBlueCliente: '',
        idEstabelecimento: '',
        idMesa: ''       
    });

    const handlerSendUserLogin = (userLoginObj) =>{
        setUserLogin(userLoginObj);
    }

    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={(props) => <TelaLogin {...props} OnSendUserLogin={handlerSendUserLogin} />} />
                <Route path="/login" component={(props) =>  <TelaLogin {...props} OnSendUserLogin={handlerSendUserLogin} />} />
                <Route path="/abrircomanda"  component={(props) => <TelaAbrirComanda {...props} userLogin={userLogin} OnSendUserLogin={handlerSendUserLogin} />} /> 
                <Route path="/scanqrcode"  component={(props) => <TelaScanQrcode {...props} userLogin={userLogin} OnSendUserLogin={handlerSendUserLogin}/>} />
                <Route path="/extrato"  component={(props) => <TelaExtrato {...props} />} />              
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
    // envolver <Routes /> com UserContext para utilizarmos como forma de login em memoria REACT
    return(
        <div className="Comanda">
            <ComandaHeader />
            <div className="content">
                <Routes />
            </div>
            <ComandaFooter />
        </div>
    );
}