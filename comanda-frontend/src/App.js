import React, { useState, useMemo } from 'react';
import Routes from './routes';
import { UserContext } from './UserContext';
import ComandaHeader from './components/layout/ComandaHeader';
import ComandaFooter from './components/layout/ComandaFooter';
import './css/base.css';

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