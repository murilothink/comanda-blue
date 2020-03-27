import React, { useState, useMemo } from 'react';
import Routes from './routes';
import { UserContext } from './UserContext';
import ComandaHeader from './components/layout/ComandaHeader';
import ComandaFooter from './components/layout/ComandaFooter';
import './css/base.css';

export default function App(){

    const [userLogin, setUserLogin] = useState(null); // inicia como null, pois nao temos user

    // Toda vez que userLogin mudar, atualiza providerUserLogin 
    const providerUserLogin = useMemo(() => ({userLogin, setUserLogin}), [userLogin, setUserLogin]);

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