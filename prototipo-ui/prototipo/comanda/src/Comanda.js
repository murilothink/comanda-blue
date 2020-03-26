import React, { Component } from 'react';
import img from './qrcode.png'
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';


class Comanda extends Component {

    render() {
        return (
            <Grid
                container style={{ height: "100%" }}
                direction="row"
                spacing={0}
                align="center"
                justify="center"
            >

                <Grid
                    container
                    style={{ height: "100%" }}
                    spacing={0}
                    align="center"
                    justify="center"
                    direction="column" item xs={12} sm={6} className="qrcode">
                    <img src={img} />
                    <Button variant="contained" color="primary">
                        Scan QRCode
                    </Button>
                </Grid>

                <Grid
                    container
                    style={{ height: "100%" }}
                    spacing={0}

                    justify="center"
                    direction="column" item xs={12} sm={6} className="PIN">
                    <TextField id="outlined-basic" placeholder="PIN" variant="outlined" />
                    <p><Button style={{ top: '72px' }} variant="contained" color="primary">
                        Confirmar PIN
                    </Button></p>
                </Grid>

            </Grid>
        )
    }
}
export default Comanda;