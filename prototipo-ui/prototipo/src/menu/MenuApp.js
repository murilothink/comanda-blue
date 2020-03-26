import React, { Component } from 'react';
import '../layout/base.css';
import ComandaHeader from '../layout/ComandaHeader';
import ComandaFooter from '../layout/ComandaFooter';
import Menu from './Menu';

class MenuApp extends Component {
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

export default MenuApp;
