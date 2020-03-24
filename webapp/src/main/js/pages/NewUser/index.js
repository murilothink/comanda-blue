import React, { useState }  from 'react';
import api from '../../services/api';

// history eh utilizado para navegacao
export default function NewUser({history}){

    // <> e </> he um fragment. React precisa que tags estejam contidas em uma outra tag.
    // Poderiamos usar DIV, mas poderia bugar css em algum caso
    return(
        <h1>Ola! New User</h1>
    )
}