# Desafio: Transações Bancárias API

Este repositório contém a implementação de uma **API RESTful** que simula funções bancárias, sendo um desafio técnico focado em **transações bancárias**, como depósitos, transferências e consultas de saldo. O objetivo é colocar em prática o conhecimento em desenvolvimento de APIs, utilizando boas práticas de codificação e arquitetura de software.

## Objetivo do Desafio

O objetivo deste desafio é construir uma **API RESTful** que simula um sistema bancário simples, onde é possível realizar as seguintes operações:

- **Criar um usuário bancário** com um saldo inicial de 0.
- **Consultar os dados do usuário**, incluindo saldo atual.
- **Realizar depósitos** na conta de um usuário.
- **Realizar transferências** entre as contas de usuários diferentes.
- **Consultar o extrato** de um usuário, mostrando suas transações realizadas.

Este projeto foi criado para testar e demonstrar o conhecimento do candidato na criação de APIs, manejo de banco de dados, e aplicação de conceitos como autenticação, validação e boas práticas de REST.

## Funcionalidades

### 1. **Criar Usuário**
- Endpoint para criar um novo usuário com dados básicos (nome, CPF, email).
- O saldo inicial de todos os usuários será `0`.

### 2. **Consultar Dados do Usuário**
- Endpoint para consultar informações de um usuário, incluindo seu saldo atual.

### 3. **Realizar Depósito**
- Endpoint que permite um depósito em um usuário específico.
- O valor do depósito deve ser positivo e será somado ao saldo do usuário.

### 4. **Realizar Transferência**
- Endpoint para realizar transferências entre usuários.
- O sistema deve garantir que o usuário remetente tenha saldo suficiente para a transferência.

### 5. **Consultar Extrato**
- Endpoint que exibe o histórico de transações de um usuário, incluindo depósitos e transferências realizadas.

## Tecnologias Utilizadas

- **Spring Boot:** Framework para o desenvolvimento da API.
- **Hibernate (JPA):** Para mapeamento objeto-relacional e manipulação do banco de dados.
- **Banco de Dados:** PostgreSQL (pode ser substituído por MySQL ou H2 para testes locais).
- **Spring Security (opcional):** Para autenticação e controle de acesso (se necessário).
- **Maven/Gradle:** Ferramentas de gerenciamento de dependências e build do projeto.

## Como Rodar o Projeto

### 1. Clonar o Repositório

Clone o repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/transacoes-bancarias-api.git
