
// TODO parar de scanear quando fizer post, somente voltar a escanear se pin invalido
// prevenir que faca diversos requests ao servidor

// TODO, Importante!!!
// Camera nao desliga ao seguir para proximo componente. Issue relatada em:
// https://github.com/JodusNodus/react-qr-reader/issues/51
//  * Portanto ao seguir para o menu, em caso de pin valido, a bolinha vermelha
//    no canto direito da aba do browser permanecera piscando! *


import React, { useState, useEffect } from 'react';
import api from '../Services/api';
import Redirector from '../Services/redirector';
// https://www.thomasbilliet.com/react-qr-reader/
import QrReader from 'react-qr-reader';

import Button from '@material-ui/core/Button';
import { Grid } from '@material-ui/core';

// Para alertas
import MessageAlert from '../Components/MessageAlert';

export default function TelaScanQrcode(props){

    // Para acessar contexto de login usuario
    const { userLogin, setUserLogin } = React.useState(props.userState);

    const [ status, setStatus ] = useState("");

    const [ values, setValues ] = useState({
        showViewFinder: true, // exibir quadrado de busca
        delay: 1000,  // intervalo tentativa scan qrcode
        result: ''  // valor do qrcode quando lido
    });

    const [mount, setMount] = React.useState(false);

    useEffect(()=>{
        if(!mount){
            let redirector =  new Redirector(props);
            redirector.checkLogado();

            setMount(true);
        }
    });

    function handleScan(rslt){
        console.log("!scan trigger!", rslt);
        if(rslt){
            setValues({ 
                //delay: 60000, // sobe delay scan para 1min, evitando ler novamente um qrcode
                result: rslt 
            });
            handleValidarPin(rslt);
        }
    }

    function clickReturnAbrirComanda() {
        props.history.push('/abrircomanda');
    }

    // Funcao assincrona para verificacao do pin. É assincrona pois deve aguardar (await) o api.post
    async function handleValidarPin(pinColetado) {
      
        // Limpa state status para nova tentativa de verificar pin
        setStatus("");
    
        try{
            // faz POST com json contendo pin para o servidor no endpoint /mesa/validatepin
            const options = {
                headers: {'COMANDA-BLUE-CLIENTE': props.userLogin.comandaBlueCliente }
            };

            const response = await api.post('/estabelecimento/mesas/' + pinColetado + '/comandas/abrir', null, options);
            
            console.log("PIN sent=" + pinColetado, response.status, response.data);
            
            // Com o pin validado, extrair idEstabelecimento e idMesa
            props.OnSendUserLogin({
                idEstabelecimento: pinColetado.split("-")[0],
                idMesa: pinColetado.split("-")[1]
            })

            props.history.push('/menu');
        }
        catch (error) {
            console.log(error);

            // Se verificacao pin foi mal sucedido, estado status muda para "pinError" e assim aciona snackbar
            setStatus("pinError");
        }     
    }
    
    function handleError(err){
        console.error(err);
    }

    const previewStyle = {
        height: 300,
        width: 300
    }

    return(
        <Grid id="telaScanQrcode">        
            <Grid
                style={{ height: "10%" }}    
                align="center"
            >
                <Grid
                    container                    
                    spacing={0}
                    align="center"
                    justify="center"
                    direction="column" item xs={12} sm={6} id="titulo"
                >
                    <h1>Aponte para o QRCODE da mesa</h1>
                </Grid>
            </Grid>

            <Grid
                align="center"
            >
                <Grid   
                    align="center"
                    item xs={12} sm={12} lg={12} id="scanQrcode"
                >
                     <QrReader
                        delay={values.delay}
                        style={previewStyle}
                        onError={handleError}
                        onScan={handleScan}
                        showViewFinder={values.showViewFinder}
                    />

                    {/* { values.result ? <MessageAlert severity="success" message={"Código lido: " + values.result} /> : null } */}
                    { status ? <MessageAlert severity="error" message={"Pin inválido"} /> : null }

                </Grid>
            </Grid>

            <Grid
                align="center"
                id="rowButtonReturnAbrirComanda"
            >
                <Button variant="contained" color="primary" style={{background: '#2d9bf0', color: 'white'}}
                    onClick={clickReturnAbrirComanda}>
                    RETORNAR
                </Button>

            </Grid>

        </Grid>
      )
}