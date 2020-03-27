import React from "react";
import "./Menu.css";
import api from "../../../services/api";

export default class MenuList extends React.Component{
    constructor(props){
        super(props);
        this.state={listaProduto:[]}
        console.log("CONSTRUTOR");
    }

    componentDidMount(){
        const url = (this.props.idCategoria===-1)?
        "/estabelecimento/"+this.props.idEstabelecimento+"/cardapio/produtos":
        "/estabelecimento/"+this.props.idEstabelecimento+"/cardapio/produtos/categoria/"+this.props.idCategoria;

        api.get(url)
        .then(response => {
            this.setState({
                listaProduto:response.data
            });
            console.log(this.state.listaProduto);    
        })
        .catch(error => {
            console.log(error);
        });
    }

    render(){
        const produtos=this.state.listaProduto.slice()
            .sort(
                (a,b) => (a.categoriaProduto.id > b.categoriaProduto.id) ? 1:-1);

        let idCategoriaAtual = -1;


        return (
                <div className="container">
                    <div className="MenuList">
                        {produtos.map((item,key) => {
                            return(<>
                                <h1 className="product-categoria">
                                    {item.categoriaProduto.categoria}
                                 </h1>
                                <ul>
                                <li className="line">
                                    <img className="product-image" src={item.imagemDoProduto}/>
                                    <button className="product-button">+</button>

                                    <h1 className="product-title">{item.nome}</h1>
                                    <p className="product-descricao">{item.descricao}</p>
                                    <h2 className="product-valor">R${item.valor.toFixed(2)}</h2>
                                </li>
                                </ul>
                                </>
                            );
                        })}
                    </div>
                </div>
            );
    }    
}
