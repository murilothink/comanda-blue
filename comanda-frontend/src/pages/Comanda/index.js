import React, { useContext } from 'react';
import { UserContext } from '../../UserContext';
import api from '../../services/api';

import img from '../../imgs/qrcode.png'
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
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

    // state para saber se pin falhou ou nao
    const [status, setStatus] = React.useState("");

    const [values, setValues] = React.useState({
        pin:'',
    });

    const handleChange = prop => event => {
        setValues({ ...values, [prop]: event.target.value });
        console.log(values);
    };

    // Funcao assincrona para verificacao do pin. É assincrona pois deve aguardar (await) o api.post
    async function handleValidarPin() {
      
        // Limpa state status para nova tentativa de verificar pin
        setStatus("");
    
        try{
            // faz POST com json contendo pin para o servidor no endpoint /mesa/validatepin
            const response = await api.post('/mesa/validatepin', { pin: values.pin});
            
            console.log("PIN sent=" + values.pin, response.status, response.data);
            
            // Com o pin validado, extrair idEstabelecimento e idMesa

            setUserLogin({
                idEstabelecimento: values.pin.split("-")[0],
                idMesa: values.pin.split("-")[1]
            })

            history.push('/menu');
        }
        catch (error) {
            console.log(error);

            // Se verificacao pin foi mal sucedido, estado status muda para "pinError" e assim aciona snackbar
            setStatus("pinError");
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
                        <h1>Olá {userLogin.nome}, seja bem vindo!</h1><br />
                        Leia o QRCode ou insira o pin da sua mesa<br />
                        token: {userLogin.comandaBlueCliente}
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
                        value={values.pin}
                        onChange={handleChange('pin')}                      
                        labelWidth={70}
                        />
                    </FormControl>

                    <p>
                        <Button 
                        style={{ top: '72px', background: '#2d9bf0', color: 'white' }} 
                        variant="contained" 
                        color="primary"
                        onClick={() => handleValidarPin()}>
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
