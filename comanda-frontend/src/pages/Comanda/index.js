import React, { Component, useContext } from 'react';
import { UserContext } from '../../UserContext';
import img from '../../imgs/qrcode.png'
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

import './style.css';

export default function Comanda() {

        // Para acessar contexto de login usuario
        const { userLogin, setUserLogin } = useContext(UserContext);

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
                    <TextField id="outlined-basic" placeholder="PIN" variant="outlined" />
                    <p>
                        <Button 
                        href="/menu"
                        style={{ top: '72px', background: '#2d9bf0', color: 'white' }} 
                        variant="contained" 
                        color="primary">
                            Confirmar PIN
                        </Button>
                    </p>
                </Grid>

            </Grid>
        </>
        )    
}