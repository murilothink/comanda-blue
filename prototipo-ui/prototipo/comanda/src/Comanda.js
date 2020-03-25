import React, { Component } from 'react';
import img from './qrcode.png'
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';


const useStyles = makeStyles(theme => ({
    root: {
      flexGrow: 1,
    },
    paper: {
      height: 140,
      width: 100,
    },
    control: {
      padding: theme.spacing(2),
    },
    button:{
        marginTop: theme.spacing(30),
    },
  }));

class Comanda extends Component{
     
    render(){
        return(
            <Grid 
            container style={{height:"100%"}}
            spacing={0}
            align="center"
            justify="center"
            className = "Comanda">

                <Grid 
              container
              style={{height:"100%"}}
              spacing={0}
              align="center"
              justify="center"
              direction="column" item xs = {12} sm = {6} className = "qrcode">
                <img src = {img} />
                <Button variant="contained" color="primary">
                    Scan QRCode
                </Button>
                    </Grid>

                <Grid 
              container
              style={{height:"100%"}}
              spacing={0}
              align="center"
              justify="center"
              direction="column" item xs = {12} sm = {6}className = "PIN">
                    <TextField id="outlined-basic" label="PIN" variant="outlined" />
                    <p><Button style = {{top: '72px'}} variant="contained" color="primary">
                        Confirmar PIN
                    </Button></p>
                </Grid>

                </Grid>     
        )  
    }
}
export default Comanda;