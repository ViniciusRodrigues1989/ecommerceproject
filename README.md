# Automação de Testes - [Ecommerce Project]

Este repositório contém os testes automatizados para o projeto [Ecommerce Project], utilizando as [Ferramentas de Automação: Selenium, Cucumber, JUni]. O objetivo deste projeto é fornecer uma suíte de testes automatizados para garantir a qualidade e estabilidade das funcionalidades do sistema.

## Estrutura do Repositório

- `src/`: Contém o código-fonte dos testes automatizados. 
  - `main/java/`: Código de suporte e bibliotecas personalizadas.
  - `test/java/`: Implementação dos testes.
    - `test/java/feature`: Cenário escrito em BDD (Gherkin);
    - `test/java/page`: Desenvolvimento de métodos que contemple as lógicas das páginas;
    - `test/java/scenarios`: Desenvolvimento de métodos que contemple as lógicas da execução dos testes.
- `resources/`: Arquivos necessários para a execução dos testes (arquivos de configuração, dados de entrada).
- `pom.xml`: Arquivo de configuração do Maven.
- `README.md`: Este arquivo de documentação.

## Tecnologias Usadas

- **Ferramenta de Automação:** [Selenium, POM (Page Object Model), Java17]
- **Framework de Testes:** [JUnit, TestNG, Cucumber, Maven]
- **Ferramentas de CI/CD:** [Jenkins]
- **Outras Tecnologias/Dependências:** [SFCC, SFDX]

## Requisitos

Antes de executar os testes, verifique se você tem as seguintes ferramentas instaladas:

- [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão [17] ou superior)
- [Maven](https://maven.apache.org/install.html) (versão [3.11] ou superior)

## Como Rodar os Testes Localmente

### 1. Clonar o Repositório

Clone o repositório para a sua máquina local:

```bash
git@github.com:ViniciusRodrigues1989/ecommerceproject.git
cd AutomationLabs
