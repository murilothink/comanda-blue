import React from "react";
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

function Alert(props) {
    // animacao exibicao snackbar
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default function MessageAlert({severity, message}){

    // Parametro severity: error, info, warning... Irao mudar cor do snackbar
    // Parametro message: mensagem que ira aparecer no snackbar


    // para guardar informacao se snackbar deve ser exibido ou nao. 
    // Sempre inicia como sendo exibido e apos um tempo ele some
    const [open, setOpen] = React.useState(true);

    // Quando der timeout componente Snackbar, ira acionar este metodo
    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }  
        setOpen(false);
    };

    return(
        <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
            <Alert onClose={handleClose} severity={severity}>
                {message}
            </Alert>
        </Snackbar>
    )

}