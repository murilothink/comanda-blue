import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Login from "./pages/Login";
import Comanda from "./pages/Comanda";
import ScanQrcode from "./pages/ScanQrcode";

import Menu from "./pages/Menu/TelaCardapio";

import Users from "./pages/User";
import NewUser from "./pages/NewUser";

export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login} />
                <Route path="/comanda" component={Comanda} />
                <Route path="/menu" component={Menu} />
                <Route path="/scanqrcode" component={ScanQrcode} />
                <Route path="/users" component={Users} />
                <Route path="/newuser" component={NewUser} />
            </Switch>
        </BrowserRouter>
    );
}