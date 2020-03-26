import React from 'react';
import Routes from './routes';
import ComandaHeader from './components/layout/ComandaHeader';
import ComandaFooter from './components/layout/ComandaFooter';
import './css/base.css';

function App(){
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

export default App;