# 📚 Library Management API

A **Library Management API** é uma aplicação RESTful desenvolvida com **Spring Boot 3.5** e **Java 21**, voltada para o gerenciamento de uma biblioteca digital. A API oferece operações completas para manipular dados de livros, autores, clientes e usuários, contando com autenticação via OAuth2 e login social com Google.

---

## Tecnologias e Ferramentas Utilizadas

- Java 21  
- Spring Boot 3.5  
- Spring Security + OAuth2  
- Spring Authorization Server  
- JWT com suporte a Refresh Token  
- Spring Data JPA  
- MapStruct (para mapeamento entre entidades e DTOs)  
- PostgreSQL  
- Lombok  
- SpringDoc + Swagger (documentação interativa)  
- Thymeleaf (páginas simples de autenticação)  
- Validações com Bean Validation  

---

## Funcionalidades

### 🔐 Autenticação e Autorização

- Autenticação via OAuth2 Authorization Code.  
- Login com conta Google (social login).  
- Geração de access token (válido por 1h).  
- Suporte a **refresh tokens**.  
- Controle de acesso com Spring Security baseado em **roles** (`OPERATOR`, `MANAGER`).  

### ✍️ Gerenciamento de Autores

- `POST /authors` - Cadastrar autor. *(Requer role OPERATOR ou MANAGER)*  
- `GET /authors/{id}` - Buscar autor por ID. *(OPERATOR ou MANAGER)*  
- `GET /authors` - Buscar autores dinamicamente por nome e nacionalidade. *(OPERATOR ou MANAGER)*  
- `PUT /authors/{id}` - Atualizar autor. *(OPERATOR ou MANAGER)*  
- `DELETE /authors/{id}` - Deletar autor. *(MANAGER)*  

### 📖 Gerenciamento de Livros

- `POST /books` - Cadastrar livro. *(OPERATOR ou MANAGER)*  
- `GET /books/{id}` - Buscar livro por ID. *(OPERATOR ou MANAGER)*  
- `GET /books` - Buscar livros por ISBN, título, nome do autor, gênero e ano de publicação. *(OPERATOR ou MANAGER)*  
- `PUT /books/{id}` - Atualizar livro. *(OPERATOR ou MANAGER)*  
- `DELETE /books/{id}` - Deletar livro. *(MANAGER)*  

### 👤 Gerenciamento de Usuários

- `POST /users` - Cadastro de usuários (para autenticação via formulário tradicional ou OAuth2). *(Não requer role)*  
- `PUT /users` - Atualizar usuário. *(OPERATOR ou MANAGER)*  
- `DELETE /users` - Deleta usuário. *(MANAGER)*  

### 🧑‍💼 Gerenciamento de Clientes

- `POST /clients` - Cadastro de novos clientes. *(MANAGER)*  

---

## ⚠️ Tratamento de Erros

- Aplicação de Exception Handler Geral

---

## 📄 Documentação Interativa

A documentação da API está disponível via Swagger UI.

> Depois de rodar a aplicação na sua máquina, acesse:  
> `http://localhost:8080/swagger-ui.html`

Inclui todos os endpoints, parâmetros esperados, respostas possíveis e exemplos.

---

## 🛠️ Como Rodar Localmente

### ✅ Pré-requisitos

- Java 21  
- Maven 3.9+  
- PostgreSQL  

---

### 📦 Passo a passo

#### 1. Clone o projeto:

```
git clone https://github.com/vanessaderigo/library-management-api.git
cd library-management-api
```

---

#### 2. Configure o banco de dados:

Certifique-se de ter um banco PostgreSQL rodando e atualize o arquivo `src/main/resources/application.yml` com suas credenciais:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/seu_banco
    username: seu_usuario
    password: sua_senha

  jpa:
    hibernate:
      ddl-auto: update
```

---

#### 3. Rode a aplicação:

Com Maven Wrapper:

```
./mvnw spring-boot:run
```

Ou, se tiver Maven instalado globalmente:

```
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.
