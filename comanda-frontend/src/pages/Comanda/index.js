import React, { Component, useContext } from 'react';
import { UserContext } from '../../UserContext';
import img from '../../imgs/qrcode.png'
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
// Para utilizar inputs. Cada FormControl pode ter 1 input
import FormControl from '@material-ui/core/FormControl';
// Password visbility, inputs
import InputLabel from '@material-ui/core/InputLabel';
import OutlinedInput from '@material-ui/core/OutlinedInput';
// Para alertas
import MessageAlert from '../../components/MessageAlert';

import './style.css';

export default function Comanda({history}){

    // Para acessar contexto de login usuario
    const { userLogin, setUserLogin } = useContext(UserContext);

    // state para saber se login falhou ou nao
    const [status, setStatus] = React.useState("");

    const [values, setValues] = React.useState({
        PIN:'',
        });

    const handleChange = prop => event => {
        setValues({ ...values, [prop]: event.target.value });
        console.log(values);
    };

    // Funcao assincrona para pedir login do usuario. É assincrona pois deve aguardar (await) o api.post
    async function handleLogarUsuario() {
      
        // Limpa state status para nova tentativa de login
        setStatus("");
    
        try{
            // faz POST com json contendo email e senha para o servidor no endpoint /usuario/logar
            const response = await api.post('/mesa/validatepin', { PIN: values.PIN});
            
            console.log(response.status, response.data);
            
            // Login OK, enviar usuario para tela de comanda
            // TODO nao seria melhor tela chamar ABRIRCOMANDA ?
            history.push('/Menu');
        }
        catch (error) {
            console.log(error);

            // Se login foi mal sucedido, estado status muda para "loginError" e assim aciona snackbar
            setStatus("PINerror");
        }     
    }

    return (
        <>
            <Grid
                container style={{ height: "30%" }}
                direction="row"
                spacing={0}
                align="center"
                justify="center"
            > 
            <Grid
                    container                    
                    spacing={0}
                    align="center"
                    justify="center"
                    direction="column" item xs={12} sm={6} className="comanda-bemvindo"
                >
                    <div className="comanda-saudacao">
                        <h1>Olá, seja bem vindo!</h1><br />
                        Leia o QRCode ou insira o pin da sua mesa
                    </div>
                </Grid>

            </Grid>

            <Grid
                container
                style={{ height: "70%" }}
                direction="row"
                spacing={0}
                align="center"
                justify="center"
            >        
                <Grid
                    container                    
                    spacing={0}
                    align="center"
                    justify="center"
                    direction="column" item xs={12} sm={6} className="qrcode">
                    <img src={img} />
                    <Button variant="contained" color="primary" style={{background: '#2d9bf0', color: 'white'}}>
                        Scan QRCode
                    </Button>
                </Grid>

                <Grid
                    container
                    spacing={0}

                    justify="center"
                    direction="column" item xs={12} sm={6} className="PIN">

                    <FormControl variant="outlined">            
                        <InputLabel htmlFor="input-PIN">PIN</InputLabel>
                        <OutlinedInput
                        id="input-PIN"
                        type='PIN'
                        value={values.PIN}
                        onChange={handleChange('PIN')}                      
                        labelWidth={70}
                        />
                    </FormControl>

                    <p>
                        <Button 
                        style={{ top: '72px', background: '#2d9bf0', color: 'white' }} 
                        variant="contained" 
                        color="primary"
                        onClick={() => handleLogarUsuario()}>
                            Confirmar PIN
                        </Button>
                    </p>
                </Grid>
            { 
            // Se estado Status tiver qualquer conteudo, como "erroLogin", mostrar snackbar
            // caso contrario, é igual nulo, entao nao mostra nada
            status ? <MessageAlert severity="error" message="PIN incorreto/invalido" /> : null
            }

            </Grid>
        </>
    )    
}
