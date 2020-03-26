import React from 'react';
import ComandaHeader from '../layout/ComandaHeader';
import ComandaFooter from '../layout/ComandaFooter';
import TelaLogin from '../login/login';
import {Route} from 'react-router-dom';
import Comanda from '../comanda/Comanda';

export default function SignIn() {
 
  return (
      <div className = 'Comanda'>
        <ComandaHeader/>
        <div className='content'>
          <Route exact path = '/' render = {()=> (<TelaLogin/>)}/>
          <Route exact path = '/comanda' render = {()=> (<Comanda/>)}/>  
        </div>
        <ComandaFooter/>
      </div>
    );
}
