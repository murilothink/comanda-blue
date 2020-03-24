import React,{Component} from 'react';

function App() {
  return (
    <div className="App">
    <ul>
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
        <button className = "login">Entrar</button>
        </div>
      </ul>
    </div>
  );
}

export default App;
