Feature: Teste de Login

  Scenario: Validar acesso ao commerce app
    Given Estou logado na homepage do Salesforce Core
    When Acesso o commerce app
    Then Devo ser direcionado para o app correto
