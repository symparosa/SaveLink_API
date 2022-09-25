# SaveLink_API

API

Framework:  Spring Boot

Linguagem: JAVA 

Documentação: https://documenter.getpostman.com/view/18953077/2s83S2DDzG

Processo de desenvolvimento: 
1.	Criei um projeto spring no visual Studio Code com todas as dependências necessárias.
2.	Criei uma classe denominada de artigos com todos os campos necessários.
3.	Criei uma classe denominada de response que é a resposta padrão da api.
4.	Criei um enum denominado de responseType que será o tipo padrão de resposta (SUCESS, ERROR).
5.	Criei uma interface denominada de artigoService com todas as funcionalidades da api.
6.	Criei uma classe denominada de artigoServiceImpl que implementa a interface artigoService, é onde será implementada todas as funcionalidades.
7.	Criei a interface artigoRepository que extende de JpaRepository<artigo, Integer>, é onde será implementado das as querys da base de dados (MySQL). 
8.	Configurei o application.properties para conectar com a base de dados 
9.	Criei uma classe denominada de artigoController para criar endpoint de acesso as funcionalidades desenvolvidas.
10.	Testei os endpoint através da ferramenta Postman.

Uso do api: em caso de quererem testar somente o api devem abrir a documentação, pegar os endpoint e fazem o teste em alguma feramente de teste de api’s. 
