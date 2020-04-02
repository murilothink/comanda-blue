import React, { useState, useMemo } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

// assets
import './style.css';
import imageLogo from './static/img/CI&T.png'

import Toolbar from '@material-ui/core/Toolbar';
import AppBar from '@material-ui/core/AppBar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import { makeStyles } from '@material-ui/core/styles';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

import TelaLogin from './Pages/TelaLogin';
import TelaAbrirComanda from './Pages/TelaAbrirComanda';
import TelaScanQrcode from './Pages/TelaScanQrcode';
import TelaExtrato from './Pages/TelaExtrato';
import TelaPagamento from './Pages/TelaPagamento';
import TelaCardapio from './Pages/TelaCardapio';

function Routes(){
    // Estados a serem guardados para login usuario
    const [userLogin, setUserLogin] = useState({
        nome: null,
        comandaBlueCliente: null,
        idComanda: null,
        idEstabelecimento: null,
        idMesa: null
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
                <Route path="/menu"  component={(props) => <TelaCardapio {...props} userLogin={userLogin} />} />
                <Route path="/extrato"  component={(props) => <TelaExtrato {...props} userLogin={userLogin} />} />   
                <Route path="/pagamento"  component={(props) => <TelaPagamento {...props} userLogin={userLogin} OnSendUserLogin={handlerSendUserLogin} />} /> 
            </Switch>
        </BrowserRouter>
    );
}


const useStyles = makeStyles((theme) => ({
    header: {
        // ...
    },
    logoCiandt: {
        height: "60%",
        marginRight: theme.spacing(2),
    },
    textHeader: {
        flexGrow: 1,
        fontFamily: 'Caveat',
        color: "white",
        margin: 0,
        fontSize: "42px",
        padding: "20px",
        fontWeight: 700,
    },
}));

function ComandaHeader(){

    const classes = useStyles();

    function handleLogout(){
        // TODO Implementar logout!!
        console.log("TODO Implementar logout!!");
    }

    return(            
        <div className={classes.header}>     
        <AppBar position="static" style={{ background: '#2d9bf0'}}>
            <Toolbar style={{ height: '60px'}}>  

                <img edge="start" className={classes.logoCiandt} 
                src={imageLogo} alt="Logo CI&T" /> 

                <Typography className={classes.textHeader}>ComandaBlue</Typography>

                <Typography>Logout</Typography>

                <IconButton
                    aria-label="logout current user"
                    onClick={handleLogout}
                    color="inherit"
                    href="/"
                > 
                    <ExitToAppIcon />
                </IconButton>
            
            </Toolbar>
        </AppBar>
        </div>
    )
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

export default function App(props){
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