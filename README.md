# :clipboard: comanda-blue

Sistema de comanda para restaurantes. Projeto desenvolvido pelos estags da GU BLUE, programa NextGen2020.

## :construction: how-to

### Clonar repositório

```
git clone https://github.com/murilothink/comanda-blue
```

### Para desenvolver o backend pelo IntelliJ

*   Abra o projeto no IntelliJ
*   Crie um novo `module` atraves do menu:

    <img src="https://raw.githubusercontent.com/murilothink/comanda-blue/master/docs/img/module-intellij/new-module-from-existing-source.PNG" width="80%">

*   Selecione a pasta `webapp`

    <img src="https://raw.githubusercontent.com/murilothink/comanda-blue/master/docs/img/module-intellij/select-folder.PNG" width="80%">

*   Importe como maven

    <img src="https://raw.githubusercontent.com/murilothink/comanda-blue/master/docs/img/module-intellij/import-as-maven.PNG" width="100%">

:heavy_exclamation_mark: Para determinar versão java do projeto:

*   Pelo `Project Explorer`, clique com o botão direito em `comanda-blue` e acesse a opção `Open Module Settings`

    <img src="https://raw.githubusercontent.com/murilothink/comanda-blue/master/docs/img/versao-java-intellij/open-module-settings.PNG" width="70%">

*   Selecione `java 1.8` em `Project SDK` e também configure `Project Language Support` como: `8 - Lambdas, type annotations, etc.`

     <img src="https://raw.githubusercontent.com/murilothink/comanda-blue/master/docs/img/versao-java-intellij/project-sdk.PNG" width="100%">


:heavy_exclamation_mark: Para podermos trabalhar com a classe EncryptDecrypt:

*   Baixar o arquivo [docs/jce_policy-8.zip](docs/jce_policy-8.zip)
*   Descompactar conteudo em `$JAVA_HOME/jre/lib/security` (diretório do java jdk que estiver utilizando)

Backend irá rodar em `http://localhost:8080`

### Para desenvolver o frontend pelo vscode

```
cd webapp/src/main/js
code .
npm install
npm start
```

Acessar frontend via `https://localhost:3000`

## :dancers: contributors

* Cristiano Andrade de Aguiar [CrisAndrade](https://github.com/CrisAndrade)
* Danilo de Nadai Sicari [dsicari](https://github.com/dsicari)
* Erik Kenzo Oura Carlini Valle [ErikKenzoValle](https://github.com/ErikKenzoValle)
* João Pedro Benicio [Jbenicio-ciandt](https://github.com/Jbenicio-ciandt)
* Luciano Alves dos Santos [Lassal](https://github.com/Lassal)
* Luciano Henrique Arendt Rodrigues [lucianohrcit](https://github.com/lucianohrcit)
* Murilo Henrique Gomes [murilothink](https://github.com/murilothink)
* Rafael Sperandio Scheiner [rscheiner-ciandt](https://github.com/rscheiner-ciandt)
* Renato Vinicius dos Santos [renato2504](https://github.com/renato2504)
* Tiago Fernandes Tasselli [tiagotasselliciandt](https://github.com/tiagotasselliciandt)
