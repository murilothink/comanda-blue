import React, {useState} from 'react';
import api from '../Services/api';

import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
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
import MessageAlert from '../Components/MessageAlert';

import '../style.css';

// No foi possivel utilizar HOOKS com: export default class Login extends React.Component
export default function TelaLogin(props){

    console.log(props);

    // state para saber se login falhou ou nao
    const [status, setStatus] = React.useState("");

    // states para recuperar email e password da pagina
    const [values, setValues] = React.useState({
      name:'',
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
        const response = await api.post('/usuario/logar', { email: values.email, senha: values.password, nome:values.name });
        
        console.log(response.status, response.data.comandaBlueCliente, response);
        
        // Se login foi bem sucedido, servidor retorna uma string e react guarda no userLogin state
        // o nome e comandaBlueCliente (que eh o email do cliente criptografado)
        props.OnSendUserLogin({
          nome: response.data.nome,
          comandaBlueCliente: response.data.comandaBlueCliente  
        });

        // Login OK, enviar usuario para tela de comanda
        // TODO nao seria melhor tela chamar ABRIRCOMANDA ?
        props.history.push('/abrircomanda');
      }
      catch (error) {
        console.log(error);

        // Se login foi mal sucedido, estado status muda para "loginError" e assim aciona snackbar
        setStatus("loginError");
      }     
    }

    return ( 
      <Container id="telaLogin" component="main" maxWidth="xs" style={{height:"100%"}}>
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
            <InputLabel htmlFor="input-name">Nome</InputLabel>
            <OutlinedInput
              id="input-name"
              type='name'
              value={values.name}
              onChange={handleChange('name')}                      
              labelWidth={70}
            />
          </FormControl>

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