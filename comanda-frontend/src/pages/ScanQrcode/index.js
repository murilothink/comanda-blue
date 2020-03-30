import React, { useState, useContext, useEffect } from 'react';
import { UserContext } from '../../UserContext';
import api from '../../services/api';
// https://www.thomasbilliet.com/react-qr-reader/
import QrReader from 'react-qr-reader';

import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';
import { Grid } from '@material-ui/core';

// Para alertas
import MessageAlert from '../../components/MessageAlert';

export default function ScanQrcode({history}){

    // Para acessar contexto de login usuario
    const { userLogin, setUserLogin } = useContext(UserContext);

    const [ status, setStatus ] = useState("");

    const [ values, setValues ] = useState({
        delay: 50,  // intervalo tentativa scan qrcode
        result: ''  // valor do qrcode quando lido
    });

    //useEffect(() => { handleValidarPin() }, [values.result]);

    function handleScan(rslt){
        console.log("!cam trigger!", rslt);
        if(rslt){
            setValues({ 
                //delay: 60000, // sobe delay scan para 1min, evitando ler novamente um qrcode
                result: rslt 
            });
        }
    }

     // Funcao assincrona para verificacao do pin, lido pelo qrcode
     // É assincrona pois deve aguardar (await) o api.post
     async function handleValidarPin() {
      
        // Limpa state status para nova tentativa de verificar pin
        setStatus("");
        //setValues({ delay: 60000 });
    
        try{
            // faz POST com json contendo pin para o servidor no endpoint /mesa/validatepin
            const response = await api.post('/mesa/validatepin', { pin: values.result});
            
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
        
        //setValues({ 
        //    delay: 50,  // delay volta ao normal
        //    result: '' 
        //});
    }
    
    function handleError(err){
        console.error(err);
    }

    const previewStyle = {
        //height: 640,
        //width: 480,
        height: 320,
        width: 240
    }

    return(
        <Container component="main" maxWidth="xs" style={{height:"100%"}}>
        <CssBaseline/>
            <Grid
                container
                style={{height:"100%"}}
                spacing={0}
                justify="center"
                direction="column"
            >            
                <h1>Aponte para o QRCode da mesa</h1><br />  

                <QrReader
                    delay={values.delay}
                    style={previewStyle}
                    onError={handleError}
                    onScan={handleScan}
                />

                { values.result ? <MessageAlert severity="success" message={"Código lido: " + values.result} /> : null }
                { status ? <MessageAlert severity="error" message={"Pin inválido"} /> : null }

            </Grid>
        </Container>
      )
}