import React from 'react';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { Grid } from '@material-ui/core';
import ComandaHeader from '../layout/ComandaHeader';
import ComandaFooter from '../layout/ComandaFooter';

export default function SignIn() {
 
  return (
      <div style={{height:"100%"}} ><ComandaHeader/>

        <Container component="main" maxWidth = "xs" style={{height:"100%"}}>
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

              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="name"
                label="Nome"
                name="name"
                autoComplete="name"
                autoFocus  
              />

              <Button 
                variant="contained" 
                style={{ background: '#2d9bf0', color: 'white'}}
              >
                Entrar
              </Button>

            </Grid>
        </Container>

        <ComandaFooter/>
      </div>
    );
}
