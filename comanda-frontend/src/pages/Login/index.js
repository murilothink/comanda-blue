import React, { useState } from 'react';
import api from '../../services/api';

import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { Grid } from '@material-ui/core';
// Para utilizar inputs. Cada FormControl pode ter 1 input
import FormControl from '@material-ui/core/FormControl';
// Password visbility, inputs
import InputLabel from '@material-ui/core/InputLabel';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import IconButton from '@material-ui/core/IconButton';
import InputAdornment from '@material-ui/core/InputAdornment';
import OutlinedInput from '@material-ui/core/OutlinedInput';
// Para alertas
import MessageAlert from '../../components/MessageAlert';

import './style.css';

// No foi possivel utilizar HOOKS com: export default class Login extends React.Component
export default function Login({history}){

    // state para saber se login falhou ou nao
    const [status, setStatus] = React.useState("");

    // states para recuperar email e password da pagina
    const [values, setValues] = React.useState({
      email: '',
      password: '',
      showPassword: false,
    });

    // quando qualquer input da pagina mudar de valor ira acionar este metodo
    const handleChange = prop => event => {
      setValues({ ...values, [prop]: event.target.value });
      console.log(values);
    };

    // quando clicar no icone para ver o password
    const handleClickShowPassword = () => {
      setValues({ ...values, showPassword: !values.showPassword });
    };

    // guarda-chuva de click mouse down, utilizado na visualizacao do password
    const handleMouseDownPassword = event => {
      event.preventDefault();
    };    

    // Funcao assincrona para pedir login do usuario. É assincrona pois deve aguardar (await) o api.post
    async function handleLogarUsuario() {
      
      // Limpa state status para nova tentativa de login
      setStatus("");

      try{
        // faz POST com json contendo email e senha para o servidor no endpoint /usuario/logar
        const response = await api.post('/usuario/logar', { email: values.email, senha: values.password });
        
        console.log(response.status, response.data.comandaBlueCliente);
        
        // Se login foi bem sucedido, servidor retorna uma string e react guarda no local storage, na chave COMANDA-BLUE-CLIENTE
        // Verificar o localstorage pelo inspecionar elemento
        localStorage.setItem('COMANDA-BLUE-CLIENTE', response.data.comandaBlueCliente);

        // Login OK, enviar usuario para tela de comanda
        // TODO nao seria melhor tela chamar ABRIRCOMANDA ?
        history.push('/Comanda');
      }
      catch (error) {
        console.log(error);

        // Se login foi mal sucedido, estado status muda para "loginError" e assim aciona snackbar
        setStatus("loginError");
      }     
    }

    return ( 
      <Container component="main" maxWidth="xs" style={{height:"100%"}}>
        <CssBaseline/>
        <Grid
          container
          style={{height:"100%"}}
          spacing={0}
          justify="center"
          direction="column"
        >
          <Typography 
            component="h1" 
            variant="h5"  
            align="center"
          >
            Bem Vindo!
          </Typography>

          <FormControl variant="outlined" style={{marginTop: "10px"}}>            
            <InputLabel htmlFor="input-email">E-mail</InputLabel>
            <OutlinedInput
              id="input-email"
              type='email'
              value={values.email}
              onChange={handleChange('email')}                      
              labelWidth={70}
            />
          </FormControl>

          <FormControl variant="outlined" style={{marginTop: "10px", marginBottom: "10px"}}>
            <InputLabel htmlFor="input-password">Password</InputLabel>
            <OutlinedInput              
              id="input-password"
              type={values.showPassword ? 'text' : 'password'}
              value={values.password}
              onChange={handleChange('password')}
              endAdornment={
                <InputAdornment position="end">
                  <IconButton
                    aria-label="toggle password visibility"
                    onClick={handleClickShowPassword}
                    onMouseDown={handleMouseDownPassword}
                    edge="end"
                  >
                    {values.showPassword ? <Visibility /> : <VisibilityOff />}
                  </IconButton>
                </InputAdornment>
              }
              labelWidth={70}
            />
          </FormControl>

            
          <Button 
            variant="contained" 
            style={{ background: '#2d9bf0', color: 'white'}}
            onClick={() => handleLogarUsuario()}
          >
            Entrar
          </Button>

          { 
            // Se estado Status tiver qualquer conteudo, como "erroLogin", mostrar snackbar
            // caso contrario, é igual nulo, entao nao mostra nada
            status ? <MessageAlert severity="error" message="Usuário/Senha incorreto!" /> : null
          }

        </Grid>
      </Container>
    )

}