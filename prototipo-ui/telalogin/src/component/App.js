import React from 'react';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { Grid } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  paper: {
    centerBlock: 'center',
    justifyContent: 'center',
    alignItems: 'center',
    display: 'flex',
    flexDirection: 'column',
  },
  
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },

}));


export default function SignIn() {
  const classes = useStyles();

    return (
        <Container component="main" maxWidth = "xs" style={{height:"100%"}}>
          <CssBaseline/>
            
            <Grid
              container
              style={{height:"100%"}}
              spacing={0}
              align="center"
              justify="center"
              direction="column"
            >

              <Typography component="h1" variant="h5">
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

              <Button variant="contained" color="primary">
                Entrar
              </Button>

            </Grid>
            
        </Container>
    );
}
