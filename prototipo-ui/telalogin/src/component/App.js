import React,{Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles(theme => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

class loginLayout extends Component {

  render(){
    return (
      <div className="App">
        <label 
        className = "labelName"> Nome </label>
        <div className= "divInputButton">
          <div>
            <input 
              className = "inputText"
              id="nameArea" 
              rows = "1" 
              cols="20"  
              maxLength = "20" 
              required></input>
          </div>
            <button 
              className = "login"
              onClick ={event => window.location.href='/'}
            >Entrar</button>
            <p>
            <Button variant="contained" color="primary">
            Primary
            </Button>
            </p>
        </div>
      </div>
    );
  }
}

export default loginLayout;
