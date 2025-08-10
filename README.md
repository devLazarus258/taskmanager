📌 Backend — README.md

# Task Manager API 🗂️

API REST para gerenciamento de usuários e tarefas, com autenticação via JWT.

## 🚀 Tecnologias
- Java 17+
- Spring Boot 3
- Spring Security + JWT
- MySQL 8
- Maven
- Swagger (OpenAPI)

## 📂 Estrutura do Backend

```plaintext
src/
└── main/
    └── java/
        └── com/
            └── exemplo/
                └── taskmanager/
                    ├── config/          # Configurações globais (ex: CORS)
                    ├── controller/      # Controladores REST (endpoints)
                    ├── model/           # Entidades JPA
                    ├── repository/      # Interfaces JPA Repository
                    ├── security/        # Configuração JWT e filtros
                    ├── service/         # Camada de serviços / regras de negócio
                    └── TaskManagerApplication.java
````

## ⚙️ Configuração

1. **Criar banco no MySQL**
```sql
CREATE DATABASE taskmanager;
````
2. **Configurar application.properties**

```plaintext
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanager?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
````

3. **Rodar a aplicação**

mvn spring-boot:run

🔑 Autenticação JWT

    /auth/register → Registrar usuário

    /auth/login → Gerar token

    Usar o token no Authorization Header:

    Authorization: Bearer SEU_TOKEN

📜 Documentação Swagger

Acesse:

http://localhost:8080/swagger-ui.html
