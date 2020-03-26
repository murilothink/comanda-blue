import AppBar from '@material-ui/core/AppBar'
import React, { Component } from 'react';
import Toolbar from '@material-ui/core/Toolbar';
import imageLogo from '../../imgs/logo.png'


export default class ComandaHeader extends Component{
    render() {
        return(            
          <div className="header">     
            <AppBar position="static" style={{ background: '#2d9bf0'}}>
              <Toolbar style={{ height: '60px'}}>
                <img src={imageLogo} alt="Logo cit" height="60px" width="60px" />
                <h2 className="textheader">Comanda - BLUE</h2>
              </Toolbar>
            </AppBar>
          </div>
        )
    }
}

