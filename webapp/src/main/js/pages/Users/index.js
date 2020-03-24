import React, { useEffect, useState }  from 'react';
import "regenerator-runtime/runtime.js"; // Sem esta dependencia nao funciona o async. Verificar!!
import api from '../../services/api';

import Button from '@material-ui/core/Button';

import './style.css';

export default function Users(){

    const [user, setUser] = useState([]); // iniciar com valor de estado de user em branco, no caso um objeto

    // Similar to componentDidMount and componentDidUpdate useEffect()
    // REF: https://reactjs.org/docs/hooks-effect.html
    useEffect(() => {
        async function loadUsers(){
            const response = await api.get('/user');
            console.log(response.data._embedded.user);
            setUser(response.data._embedded.user);
        }
        loadUsers();
    }, []);

    // <> e </> sao tags chamadas de fragments. React necessita retorno esteja contido entre tags.
    // nao utilizar <div> pois pode bugar com CSS
    return(
        <>
            <Button variant="contained">Default</Button>

            <ul className="users">
                {user.map(u => (
                    <li key={u.id}>
                        Nome: {u.name}, e-mail: {u.email}
                    </li>
                ))}
            </ul>
        </>
    )
}