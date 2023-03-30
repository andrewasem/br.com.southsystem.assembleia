# Desafio Técnico
## Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

# Requisitos para poder desenvolver
## Java
* Instalar versão do java jdk-11.0.8

## docker
* Verficar versão mais recente ou versão atual 3.8
* Instalar versão do docker compose

## Lombok
* Configurar lombok na ferramenta de desenvolvimento

## Gradle
* Realizar configuração do gradle para versão do java

# Ambiente Local de Desenvolvimento
Para configurar o ambiente local de desenvolvimento, basta acessar a pasta raiz do projeto, abrir o terminal e executar o comando docker-compose up -d

# Executar a aplicação no docker
Basta acessar a raiz do projeto, abrir o prompt de comando e executar o comando docker-compose up --build, isso fará com que a aplicação 
seja compilada e todas as suas dependências sejam instaladas diretamente no docker.

# Accessar collection do postman para verificar os serviços
Na raiz do projeto se encontra um arquivo json "SouthSystem.postman_collection.json" que está com os json dos endpoints desenvolvidos
Basta apenas realizar o import em seu Postman

# Serviços desenvolvidos
Foram desenvolvidos ao total 5 endpoints com testes unitários e testes de integração para a proposta levantada.
* 1 - serviço para [Cadastrar Pauta](http://localhost:8080/assembleia/v1/pauta)
* 2 - serviço para [Cadastrar Associado](http://localhost:8080/assembleia/v1/associado)
* 3 - serviço para [Cadastrar Sessão](http://localhost:8080/assembleia/v1/sessao)
* 4 - serviço para [Cadastrar Voto](http://localhost:8080/assembleia/v1/pauta)
* 5 - serviço para [Listar Pautas com Qtd. de Votos](http://localhost:8080/assembleia/v1/pauta//listar)

# Arquitetura
Em termos de arquitetura foi utilizado java 11 como linguagem de programação com WebFlux, 
também foram utilizados o lombok e mapstructor para facilitar o desenvolvimento e Liquibase para controle de versionamento de scripts de banco,
dando mais agilidade para o desenvolvedor.
Em termos de estrutura da aplicação ela ficou dividida em dois módulos, um para a parte de serviço e acesso aos repositórios (Service, Repository e Entities) e 
outro módulo para parte contrato (Controllers, Facade, Response, Request e Mappers). 
Na parte de banco de dados foi optado por colocar um modelo relacional, mas pode ser facilmente alterado para mongodb (NoSQL), porém para esse 
cenário em específico não se apresentou necessário sua utilização.
Como ferramento de Data Base Adm ficou o PgAdmin para poder realizar consultas aos dados persistidos.
No mais todo o projeto executa no docker, facilmente acessado, testado e aplicado em prática.

# Swagger
Acesso a documentação do [Swagger](http://localhost:8080/assembleia/swagger-ui/index.html#)


# Referências
- [Docker](https://docs.docker.com/engine/reference/commandline/docker)
- [Docker Compose + PostgreSQL](https://docs.docker.com/compose/django/)
- [Docker Compose + PostreSQL](https://docs.docker.com/engine/examples/postgresql_service/)
- [Docker Compose + PgAdmin](https://forums.docker.com/t/postgres-and-pgadmin-docker-compose-static-ip-connection-error/81309)