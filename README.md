# Sistema de Gerenciamento de Eventos

Este projeto é um sistema completo de gerenciamento de eventos que oferece aos usuários a capacidade de gerenciar eventos e participantes de forma eficiente. Utiliza Docker e Docker Compose para facilitar a configuração e o Flyway para o gerenciamento dos esquemas de banco de dados.

A aplicação permite aos organizadores cadastrar eventos e abrir páginas públicas de inscrição. Os participantes inscritos podem gerar uma credencial para check-in no dia do evento. Além disso, o sistema realiza a leitura da credencial do participante para permitir sua entrada no evento.

### Tecnologias

- JDK 17
- Spring Boot
- Spring Security
- Maven
- Swagger
- PostgreSQL
- Docker
- Docker Compose
- Flyway
- Lombok

## Requisitos Funcionais

- [x] Organizadores devem poder cadastrar novos eventos.
- [x] Organizadores devem poder visualizar os detalhes de um evento.
- [x] Organizadores devem poder visualizar a lista de participantes de um evento.
- [x] Participantes devem poder se inscrever em eventos.
- [x] Participantes devem poder visualizar seu crachá de inscrição.
- [x] Participantes devem poder realizar check-in no evento.

## Regras de Negócio

- [x] Um participante só pode se inscrever em um evento uma única vez.
- [x] Um participante só pode se inscrever em eventos com vagas disponíveis.
- [x] Um participante só pode realizar check-in em um evento uma única vez.

# Pré-requisitos para instalação

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Docker
- Docker Compose

## Instalação

Para instalar a aplicação, siga esses passos:

- Clone o repositorio do GitHub:

```shel
  https://github.com/GabrielMorais2/eventos.git
```

- Navegue até o diretorio da aplicação:

```shel
  cd eventos
```

- Execute o docker compose para instalar iniciar a aplicação:

```shel
  docker-compose up -d
```

A aplicação estará disponivel em http://localhost:8080.

## Uso

O projeto expõe dois principais controllers RESTful:

### Eventos

- GET /v1/events/{id}: Obtém os detalhes de um evento específico. Requer token JWT.
- POST /v1/events: Cria um novo evento. Requer token JWT.
- POST /v1/events/{eventId}/attendees: Registra um participante em um evento. Requer token JWT.
- GET /v1/events/{eventId}/attendees: Obtém uma lista de todos os participantes de um evento. Requer token JWT.

### Participantes (Attendees)

- GET /v1/attendees/{attendeeId}/badge: Recupera os detalhes da credencial para um participante específico. Requer token JWT.
- POST /v1/attendees/{attendeeId}/check-in: Registra o check-in de um participante no evento. Requer token JWT.

### Usuários

- POST /v1/users/register: Registra um novo usuário.
- POST /v1/users/login: Autentica um usuário e gera um token JWT para acesso aos recursos protegidos.

Obs: O banco de dados é inicializado com arquivos de teste para preencher a base de dados. Atualmente, há 1 evento e 17 participantes cadastrados nele.

Além disso, um usuário administrador já está criado para fins de teste, com o seguinte login e senha:

- E-mail: admin@teste
- Senha: 12345678

Esse usuário pode ser utilizado para obter o token JWT necessário para acessar os endpoints protegidos.

## Documentação da API

A API está devidamente documentada utilizando anotações Swagger/OpenAPI. Você pode acessar a interface do usuário do Swagger em http://localhost:8080/events/swagger-ui/index.html# para interagir com a API e visualizar os endpoints disponíveis.

Além disso, uma coleção do Postman está disponível na pasta ./docs/. Para utilizá-la, basta importá-la na aplicação Postman.

![Captura de tela 2024-04-15 115757](https://github.com/GabrielMorais2/eventos/assets/68476116/609a8b2a-e4c4-4ac2-b217-d8e7eb277a91)

## Funcionalidades para implementar:

- Frontend com React.
