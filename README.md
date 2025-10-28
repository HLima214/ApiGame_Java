# JWT implementado + Testes com Mock

## Como a autenticação JWT foi implementada
1. **Dependências**: `spring-boot-starter-security`, `jjwt` (api/impl/jackson).
2. **JwtService** (`src/main/java/com/curso/gameapi/security/JwtService.java`)
   - Gera tokens **HS256** usando uma chave secreta (`jwt.secret`) e tempo de expiração (`jwt.expiration`).
   - Expõe utilitários para extrair `subject` (username) e validar expiração.
3. **JwtAuthFilter** (`src/main/java/com/curso/gameapi/security/JwtAuthFilter.java`)
   - Lê `Authorization: Bearer <token>` a cada request.
   - Valida o token e popula o `SecurityContext` com um `UsernamePasswordAuthenticationToken`.
4. **SecurityConfig** (`src/main/java/com/curso/gameapi/api/config/SecurityConfig.java`)
   - Stateless (`SessionCreationPolicy.STATELESS`), CSRF desabilitado para APIs.
   - Libera `/auth/**` e Swagger; os demais endpoints exigem autenticação.
   - Registra `JwtAuthFilter` **antes** do `UsernamePasswordAuthenticationFilter`.
   - Define `UserDetailsService` **in-memory** (usuários `admin/admin` e `user/user`) apenas para exemplo.
5. **AuthController** (`/auth/login`)
   - Recebe `('username', 'password')`, autentica via `AuthenticationManager` e emite token com claim `role`.

### Configuração de execução (H2 em memória por padrão)
- `application.yml` define H2 **em memória** e **Flyway** ativo.
- Migration `V1__init.sql` cria tabelas `game` e `player` com colunas alinhadas às entidades (`id_game`, `id_player`, `fav_game`).
- `ddl-auto: validate` garante que o Hibernate **apenas valida** o que o Flyway criou.

**Executar:**
```bash
mvn -q clean package
mvn spring-boot:run
# Swagger: http://localhost:8080/swagger-ui
```

**Login e uso do token:**
```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}'
curl http://localhost:8080/api/games -H "Authorization: Bearer <TOKEN>"
```

**Banco H2**
```bash
# H2: http://localhost:8080/h2-console
# url:jdbc:h2:mem:bankdb
# UserName: teste
# Password: 1234
```

## Testes com Mock
- `JwtServiceTest`: valida geração e verificação do token.
- `AuthControllerTest` (Mockito):
  - **Mock** de `AuthenticationManager` para retornar um `Authentication` sintético.
  - **Spy** de `JwtService` para forçar o retorno de `"fake-token"` e isolar a lógica do controller.
  - Verifica invocações (`verify`) e o payload da resposta.

**Rodar testes:**
```bash
mvn -q -DskipITs test
```

## Desafios de Melhorias (próximos passos - Review 1/2)
1. **Identificar Melhorias**
   - Implementar pacote Service para Game e Player e refatorar o código.
2. **Banco de Dados**
   - Implementar uma nova config para banco de dados (Postgre/Mysql/Oracle)
2. **Implementar Docker**
    - Validar a possibilidade de subida da aplicação via Docker utilizando outro banco de dados e descrever como esses 
passos seriam realizados!