export default class Redirector{
    constructor(props){
        this.props = props;
    }
    //TODO
    //Checa as informacoes vindas do pai
    //e no caso de algum estar faltante
    //redireciona o usuario
    checkLogado(){
        if(!this.props.userLogin.comandaBlueCliente){
            this.props.history.push("/login");
        }
    }

    checkComanda(){
        if(!this.props.userLogin.idComanda){
            this.props.history.push("/abrirComanda");
        }
    }
}