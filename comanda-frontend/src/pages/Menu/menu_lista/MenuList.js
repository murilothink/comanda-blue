import React from "react";
import "./Menu.css";
import api from "../../../services/api";

function Categoria(props){
    return(
        <>             
        <h1 className="product-categoria">
            {props.categoria}
        </h1>
        <ul id={props.id}>
            {props.produtos.map((item, key)=>{
                const handleClick = () =>{
                    props.OnItemClick(item);
                }

                return(
                <li className="line">
                    <img className="product-image" src={item.imagemDoProduto}/>
                    <button onClick={handleClick} className="product-button">+</button>

                    <h1 className="product-title">{item.nome}</h1>
                    <p className="product-descricao">{item.descricao}</p>
                    <h2 className="product-valor">R${item.valor.toFixed(2)}</h2>
                </li>
                );
            })}
        
        </ul>
        </>
    );
}

export default class MenuList extends React.Component{
    constructor(props){
        super(props);
        this.state={listaProduto:[]}
    }

    componentDidMount(){
        const url = (this.props.idCategoria===-1)?
        "/estabelecimento/"+this.props.idEstabelecimento+"/cardapio/produtos":
        "/estabelecimento/"+this.props.idEstabelecimento+"/cardapio/produtos/categoria/"+this.props.idCategoria;

        api.get(url)
        .then(response => {
            this.setState({
                listaProduto: response.data
            });  
        })
        .catch(error => {
            console.log(error);
        });
    }

    render(){
        const produtos=this.state.listaProduto.slice();
        produtos.sort(
            (a,b) => (a.categoriaProduto.id > b.categoriaProduto.id) ? 1:-1
        );

        let idCategoriaAtual = -1;
        let categorias = [];
        let produtosDaCategoria;
        let i = -1;

        produtos.forEach((item, key) => {
            if(item.categoriaProduto.id != idCategoriaAtual){
                idCategoriaAtual = item.categoriaProduto.id;
                produtosDaCategoria = [item];
                categorias.push(
                    <Categoria id={item.categoriaProduto.id}
                               categoria={item.categoriaProduto.categoria}
                               produtos={produtosDaCategoria}
                               OnItemClick={(produto) =>{this.props.OnAddItem(produto)}}
                    />
                );
                i++;
            }
            else{
                produtosDaCategoria.push(item);
                categorias[i] = <Categoria id={item.categoriaProduto.id}
                                           categoria={item.categoriaProduto.categoria}
                                           produtos={produtosDaCategoria}
                                           OnItemClick={(produto) =>{this.props.OnAddItem(produto)}}
                                />;
            }
        });

        return (
            <div className="container">
                <div className="MenuList">
                    {categorias}
                </div>
            </div>
        );
    }    
}
