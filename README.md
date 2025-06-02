<h1 align="center"> CRUD - Atividade Uniasselvi </h1>

Este projeto é uma aplicação Java que implementa um sistema de gerenciamento de entregas, permitindo o cadastro e a gestão de entregadores, veículos, produtos e entregas. Desenvolvido como parte de uma atividade acadêmica na Uniasselvi, o sistema realiza operações CRUD (Create, Read, Update, Delete) com persistência em banco de dados MySQL.

## 📁 Estrutura do Projeto
├── src/
│ ├── dao/
│ │ ├── EntregadorDAO.java
│ │ ├── VeiculoDAO.java
│ │ ├── ProdutoDAO.java
│ │ └── EntregaDAO.java
│ ├── model/
│ │ ├── Entregador.java
│ │ ├── Veiculo.java
│ │ ├── Produto.java
│ │ ├── Entrega.java
│ │ └── Status.java
│ ├── view/
│ │ ├── AbaEntregadorPanel.java
│ │ ├── AbaVeiculoPanel.java
│ │ ├── AbaProdutoPanel.java
│ │ ├── AbaEntregaPanel.java
| | └── TelaPrincipal.java
│ └── conexao/
│ └── ConexaoDAO.java

## ⚙️ Tecnologias Utilizadas
- **Java 8+**
- **Swing** (Interface gráfica)
- **MySQL** (Banco de dados relacional)

## ⚙️ Funcionalidades

- **Entregador**: Cadastro, consulta, atualização e remoção de entregadores.
- **Veículo**: Gerenciamento de veículos utilizados nas entregas.
- **Produto**: Registro de produtos disponíveis para entrega.
- **Entrega**: Associação de entregadores, veículos e produtos para realizar uma entrega.

## 🔌 Configuração do Banco de Dados

1. Crie o banco de dados utilizando o script `Script_Geracao_mydb.txt` disponível no repositório.

2. Configure a conexão no arquivo `ConexaoDAO.java`:

## 📌 Requisitos
- JDK ou superior
- MySQL Server
