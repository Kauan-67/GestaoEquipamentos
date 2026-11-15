# 🖥️ Sistema de Gestão de Equipamentos de Laboratório

Aplicação CRUD desenvolvida em **Java** com conexão a banco de dados **MySQL** via **JDBC**, estruturada sob os padrões arquiteturais **MVC (Model-View-Controller)** e **DAO (Data Access Object)**.

---

## 📋 Funcionalidades

- **Cadastrar equipamento:** Registra novos itens (computadores, projetores, etc.) validando dados obrigatórios e impedindo números de patrimônio duplicados.
- **Listar equipamentos:** Exibe todos os equipamentos cadastrados no banco de dados.
- **Buscar por patrimônio:** Consulta rapidamente um item específico pelo seu número de patrimônio.
- **Atualizar equipamento:** Permite editar as informações e a situação de um equipamento existente.
- **Excluir equipamento:** Remove um equipamento do sistema a partir do número de patrimônio.

---

## 🏗️ Estrutura do Projeto

```text
GestaoEquipamentos/
├── lib/
│   └── mysql-connector-j-26.7.0.jar   # Driver JDBC oficial do MySQL
├── src/
│   ├── conexao/
│   │   └── Conexao.java               # Fábrica de conexões com o MySQL
│   ├── model/
│   │   └── Equipamento.java           # Classe entidade (molde do equipamento)
│   ├── dao/
│   │   └── EquipamentoDAO.java        # Operações SQL (INSERT, SELECT, UPDATE, DELETE)
│   ├── controller/
│   │   └── EquipamentoController.java # Regras de negócio e validações
│   ├── view/
│   │   └── MenuView.java              # Interface de menu interativo no console
│   └── App.java                       # Classe principal (ponto de entrada)
└── bdlaboratorio.sql                  # Script de criação do banco e da tabela
