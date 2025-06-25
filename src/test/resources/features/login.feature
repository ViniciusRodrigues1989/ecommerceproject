Feature: Teste de Login

  Scenario: Validar acesso usuario e senhas validas

    Given Acesso a homepage do commerce
    When Clico em login
    Then Valido Login Sucesso

  Scenario: Login usuário Bloqueado
    Given Acesso a homepage do commerce com usuario bloqueado
    When Clico em login
    Then Devo ver a mensagem de erro user has been locked out