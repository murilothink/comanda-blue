import React,{Component} from 'react';

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
        </div>
      </div>
    );
  }
}

export default loginLayout;
