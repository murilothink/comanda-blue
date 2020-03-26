import React, { Component } from 'react';
import './base.css';
import ComandaHeader from './ComandaHeader';
import ComandaFooter from './ComandaFooter';
import Menu from '../menu/Menu';

class App extends Component {
    render(){
        return (
          <div className="Comanda">
            <ComandaHeader />
            <div className = "content">
              <Menu />
            </div>
            <ComandaFooter />
          </div>
        );
    }
}

export default App;
