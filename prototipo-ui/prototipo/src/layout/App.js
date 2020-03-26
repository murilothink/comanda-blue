import React, { Component } from 'react';
import './base.css';
import ComandaHeader from './ComandaHeader';
import ComandaFooter from './ComandaFooter';

class App extends Component {
    render(){
        return (
          <div className="Comanda">
            <ComandaHeader />
            <div className = "content">
              
            </div>
            <ComandaFooter />
          </div>
        );
    }
}

export default App;
