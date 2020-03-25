import React, { Component } from 'react';
import './base.css';
import ComandaHeader from './ComandaHeader';
import ComandaFooter from './ComandaFooter';
import Comanda from '../Comanda';

class App extends Component {
    render(){
        return (
          <div className="Comanda">
            <ComandaHeader />
            <div className = "content">
              <Comanda />
            </div>
            <ComandaFooter />
          </div>
        );
    }
}

export default App;
