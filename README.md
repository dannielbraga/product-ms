# Product Ms
Microserviço responsável por provê um catálogo de produtos. 

### Resumo das funcionalidades dos Endpoints

* Cadastrar um novo produto
* Atualizar um produto com base no id informado, e nas informações que devem ser atualizadas
* Buscar os dados de um produto, filtrando pelo id do mesmo
* Buscar a listagem de todos os produtos disponíveis
* Deleter um produto da base de dados, com base no id informado
* Pesquisar produtos com base em alguns critérios: q (string informada que é filtrada no name e description do produto), 
min_price e max_price (valores monetários que servem de valor mínimo e máximo dos produtos que queremos trazer)
  
### Links úteis

* Swagger UI documentation: http://localhost:9999/swagger-ui.html
* H2 Database console: http://localhost:9999/h2-console/

### Ferramentas e tecnologias utilizadas

* RESTful
* Maven
* Java 8  
* Spring Boot 2.4.3  
* JUnit 5 Jupiter
* Mockito 2.23.0  
* Hibernate
* H2 Database
* Project Lombok
* Swagger 2.9.2


