import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Users from "./pages/User";
import NewUser from "./pages/NewUser";

export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Users} />
                <Route path="/users" component={Users} />
                <Route path="/newuser" component={NewUser} />
            </Switch>
        </BrowserRouter>
    );
}