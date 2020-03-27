import AppBar from '@material-ui/core/AppBar'
import React, { Component } from 'react';
import Toolbar from '@material-ui/core/Toolbar';
import imageLogo from '../../imgs/CI&T.png'


export default class ComandaHeader extends Component{
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

