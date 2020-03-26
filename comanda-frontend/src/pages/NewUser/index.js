import React, { useState }  from 'react';
import api from '../../services/api';

// history eh utilizado para navegacao, semlhante ao esquema de uma pilha
export default function NewUser({history}){

    const [email, setEmail] = useState(""); // iniciar com valor de estado em branco, no caso uma string
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");


    // Funcao assincrona pois utilizamos await em api.post !
    async function handleSubmit(event){
        // Nao deixa o componente ter o comportamento normal, no caso o Submit de ir para outra pagina
        event.preventDefault(); 
        console.log(email, name, password);
        
        // Aguarda retorno do post
        // Passar o json com dados para inserir no banco. Quando atributoJson = state podemos fazer
        // password: password -> como apenas password. Tanto faz neste caso! Mesma coisa poderia ser
        // feita em email e name
        const response = await api.post("/user", {
            email: email,
            name: name,
            password
        });

        if(response.status == 201){
            console.log("OK, user created");
            alert("OK, user created");            
        }
        else{
            // TODO nao esta entrando aqui, verificar
            console.log("Error: user not created");
            alert("Error: user not created");
        }

        // manda para rota '/'
        history.push('/');
    }

    // <> e </> he um fragment. React precisa que tags estejam contidas em uma outra tag.
    // Poderiamos usar DIV, mas poderia bugar css em algum caso
    return(
        <form onSubmit={handleSubmit}>
            <label htmlFor="email">E-mail</label>
            <br />
            <input
                id="email"
                type="text"
                placeholder="e-mail"
                // Toda vez que user mudar valor input ocorrera um evento e event.target.value tera o novo valor
                // entao usar setEmail para atualizar estado
                onChange={event => setEmail(event.target.value)}
                // Para manter campo atualizado
                value={email}
            />
            <br /><br />

            <label htmlFor="name">Name</label>
            <br />
            <input
                id="name"
                type="text"
                placeholder="name"
                onChange={event => setName(event.target.value)}
                value={name}
            />
            <br /><br />

            <label htmlFor="password">Password</label>
            <br />
            <input
                id="password"
                type="text"
                placeholder="password"
                onChange={event => setPassword(event.target.value)}
                value={password}
            />
            <br /><br />

            <button className="btn" type="submit">Send</button>

        </form>
    )
}