# 🚀 API RestFull de Gerenciamento Corporativo 

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="30" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="30" alt="spring logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" height="30" alt="postgresql logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vscode/vscode-original.svg" height="30" alt="vscode"  />
  <img width="12" />
</div>

Uma API RESTful robusta desenvolvida para consolidar conhecimentos no ecossistema **Spring Boot**, persistência de dados relacional e automação de deploys/testes com esteiras de **CI/CD**. O projeto simula o backend de um sistema de gerenciamento comercial (baseado na estrutura de padaria/vendas).

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Linguagem Principal:** Java 11
* **Framework Core:** Spring Boot (Spring Web)
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Gerenciador de Dependências:** Maven
* **DevOps / CI/CD:** GitHub Actions (Automação de Build e Testes)

## ⚙️ Funcionalidades do Sistema

* **Operações CRUD Completas:** Criação, leitura, atualização e exclusão de entidades de negócio.
* **Mapeamento Objeto-Relacional (ORM):** Relacionamentos complexos mapeados nativamente via Hibernate.
* **Persistência Segura:** Estrutura de banco de dados baseada em scripts SQL relacionais otimizados.
* **Integração Contínua (CI):** Verificação automatizada de integridade do código a cada `git push` na branch principal.

## 🛣️ Endpoints da API

Abaixo estão listadas as rotas principais da aplicação para o gerenciamento do ecossistema comercial:

| Recurso | Método | Endpoint | Descrição |
| :--- | :--- | :--- | :--- |
| **Produtos** | `GET` | `/api/produtos` | Lista todos os produtos com paginação |
| | `GET` | `/api/produtos/{id}` | Busca os detalhes de um produto específico |
| | `POST` | `/api/produtos` | Cadastra um novo produto no estoque |
| | `PUT` | `/api/produtos/{id}` | Atualiza informações de preço ou estoque |
| | `DELETE`| `/api/produtos/{id}` | Remove um produto do catálogo |
| **Clientes** | `GET` | `/api/clientes` | Retorna a lista de clientes cadastrados |
| | `POST` | `/api/clientes` | Cria um novo perfil de cliente |
| **Vendas** | `POST` | `/api/vendas` | Registra um novo pedido/venda e baixa no estoque |

> 💡 *Nota: Caso os caminhos (`/api/...`) ou nomes das entidades no seu código Java sejam ligeiramente diferentes, você pode alterar os termos desta tabela diretamente para refletir as suas classes `@RestController`.*

## 📁 Estrutura do Banco de Dados

O projeto utiliza o PostgreSQL como motor de banco de dados. Um exemplo da modelagem relacional utilizada para os testes de regras de negócio pode ser encontrado no arquivo raiz:
* `teste-padaria.sql`: Script contendo a estrutura de tabelas e inserções iniciais para validação das rotas da API.

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
* Java JDK 11 instalado
* Maven instalado
* PostgreSQL configurado e rodando localmente

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com
   cd ProjetoBackEnd
   ```

2. **Configure o Banco de Dados:**
   Abra o arquivo `src/main/resources/application.properties` (ou `application.yml`) e ajuste as credenciais do seu PostgreSQL local:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. **Execute os scripts iniciais:**
   Instancie as tabelas utilizando o arquivo `teste-padaria.sql` no seu cliente de banco de dados (ex: pgAdmin, DBeaver).

4. **Rode a aplicação:**
   ```bash
   mvn spring-boot:run
   ```
   A API estará disponível em `http://localhost:8080`.

## 🔄 Esteira de CI/CD (GitHub Actions)

Este repositório conta com um fluxo de **Integração Contínua (CI)** configurado via GitHub Actions (`.github/workflows/maven.yml`). A cada atualização de código enviada ao repositório, a esteira executa de forma automática:
1. O download e cache das dependências do Maven.
2. A compilação completa do código Java 11.
3. A execução de testes e validação de pacotes corporativos (`mvn clean verify`).

---
Desenvolvido por [David Nicolini](https://github.com).
