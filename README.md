# Material Control API

Bem-vindo ao projeto **Material Control API**, uma aplicação desenvolvida para gerenciar o controle de materiais de almoxarifado. Este sistema é composto por uma arquitetura de microsserviços.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-%23F7B93E.svg?&style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%23A6B5E4.svg?&style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-%2342A5F5.svg?&style=for-the-badge&logo=springcloud&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-%232496ED.svg?&style=for-the-badge&logo=docker&logoColor=white)
![Eureka Server](https://img.shields.io/badge/Eureka%20Server-%2366CC66.svg?&style=for-the-badge&logo=spring&logoColor=white)
![Insomnia](https://img.shields.io/badge/Insomnia-%23F0F0F0.svg?&style=for-the-badge&logo=insomnia&logoColor=black)

## Estrutura do Projeto

O repositório está organizado nas seguintes pastas e arquivos principais:

- **eurekaserver**: Serviço de registro e descoberta para os microsserviços.
- **ms-cloudgateway**: Gateway responsável por roteamento e autenticação das requisições.
- **ms-loan**: Microsserviço para gerenciamento de empréstimos de materiais.
- **ms-materials**: Microsserviço para gerenciamento de materiais no almoxarifado.
- **ms-users**: Microsserviço para gerenciamento de usuários.
- **docker-compose.yml**: Arquivo para orquestração dos serviços utilizando Docker.
- **Insomnia_Material_Control_API.json**: Arquivo com os endpoints da API para importação no Insomnia.

## Pré-requisitos

Antes de começar, certifique-se de ter instalado:

[![Java 17+](https://img.shields.io/badge/Java%2017%2B-%23F7B93E.svg?&style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-%23C71F37.svg?&style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/install.html)
[![Docker](https://img.shields.io/badge/Docker-%232496ED.svg?&style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/get-docker/)
[![Insomnia](https://img.shields.io/badge/Insomnia-%23F0F0F0.svg?&style=for-the-badge&logo=insomnia&logoColor=black)](https://insomnia.rest/download)
## Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/jowgaze/material-control.git
   cd material-control
   ```

2. Configure as variáveis de ambiente necessárias para cada microsserviço (Opicional).

3. Utilize o **Docker Compose** para subir os serviços:

   ```bash
   docker-compose up
   ```

4. Acesse o Eureka Server para verificar os serviços registrados:

   - URL: `http://localhost:8761`

5. Teste os endpoints utilizando o Insomnia:

   - Importe o arquivo `Insomnia_Material_Control_API.json` no Insomnia.

## Endpoints Principais

Todos os endpoints estão disponíveis através do gateway:

- Base URL do Gateway: `http://localhost:8080`

| Microsserviço   | Endpoint                                 | Descrição                               |
|-----------------|------------------------------------------|-----------------------------------------|
| **ms-users**    | POST /api/v1/users                       | Cadastro de usuários                    |
|                 | GET /api/v1/users                        | Listagem de usuários                    |
|                 | GET /api/v1/users/{id}                   | Detalhes de um usuário                  |
|                 | PUT /api/v1/users/{id}                   | Atualizar dados de um usuário           |
|                 | PATCH /api/v1/users/{id}/password        | Atualizar senha de um usuário           |
|                 | PATCH /api/v1/users/{id}/employee-status | Ativar role employee de um usuário      |
| **ms-materials**| POST /api/v1/materials                   | Cadastro de materiais                   |
|                 | GET /api/v1/materials                    | Listagem de materiais                   |
|                 | GET /api/v1/materials/{id}               | Detalhes de um material                 |
|                 | DELETE /api/v1/materials/{id}            | Remoção de um material                  |
|                 | POST /api/v1/items                       | Cadastro de items                       |
|                 | GET /api/v1/items                        | Listagem de items                       |
|                 | GET /api/v1/items/{id}                   | Detalhes de um item                     |
|                 | PUT /api/v1/items/{id}/update            | Atualização de um item                  |
|                 | DELETE /api/v1/items/{id}                | Remoção de um material                  |
| **ms-loan**     | POST /api/v1/loans                       | Registro de empréstimos                 |
|                 | GET /api/v1/loans/{id}                   | Detalhe simples de um empréstimos       |
|                 | GET /api/v1/loans/{id}/full              | Detalhe completo de um empréstimo       |
|                 | PUT /loans/{id}/return                   | Registro de devolução de material       |


## Contribuindo

Se você deseja contribuir com o projeto:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção:

   ```bash
   git checkout -b minha-feature
   ```

3. Realize suas alterações e faça o commit:

   ```bash
   git commit -m "Minha contribuição"
   ```

4. Envie para o seu fork e abra um Pull Request.

## Melhorias Pendentes

- **Implementação de Segurança e Autenticação JWT**
  - [ ] **Autenticação JWT**: Implementar a autenticação usando JSON Web Tokens (JWT) para garantir que apenas usuários autenticados possam acessar áreas restritas do sistema.
  - [ ] **Validação de Tokens**: Adicionar a validação do token JWT em todas as rotas que exigem autenticação.

- **Serviço de Mensageria**
  - [ ] **Integração de Serviço de Mensageria**: Integrar um serviço de mensageria (por exemplo, RabbitMQ, Kafka) para gerenciar a comunicação assíncrona entre diferentes componentes do sistema.
  - [ ] **Gerenciamento de Fila**: Configurar a fila de mensagens para garantir que as mensagens sejam processadas de maneira eficiente e sem perdas.
  - [ ] **Monitoramento de Mensagens**: Implementar monitoramento para verificar a entrega e o processamento de mensagens com falhas ou atrasos.

- **Envio de Email**
  - [ ] **Integração com Serviço de E-mail**: Implementar integração com um serviço de envio de e-mails (como SendGrid, Amazon SES, ou SMTP) para envio de notificações, recuperações de senha, etc.
  - [ ] **Templates de E-mail**: Criar templates para os diferentes tipos de e-mails, incluindo emails de boas-vindas, recuperação de senha e notificações de sistema.

**Desenvolvido com 💻 e ☕ por [jowgaze](https://github.com/jowgaze)**
