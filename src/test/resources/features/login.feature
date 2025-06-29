Feature: Teste de Login

  Scenario: Validar acesso usuario e senhas validas

    Given Acesso a homepage do commerce
    When Clico em login
    Then Valido Login Sucesso

  Scenario: Login usuário Bloqueado
    Given Acesso a homepage do commerce com usuario bloqueado
    When Clico em login
    Then Devo visualizar a mensagem de erro user has been locked out

  Scenario: Login usuário valido e senha errada
    Given Acesso a homepage do commerce com usuario valido e senha errada
    When Clico em login
    Then Devo visualizar a mensagem usuario ou senha invalidos

  Scenario: Tentativa login sem usuario preenchido
    Given Acesso a homepage do commerce com usuario vazio e senha em branco
    When Clico em login
    Then Mensagem usuario obrigatorio sera exibida

  Scenario: Tentativa login sem senha preenchida
    Given Acesso a homepage do commerce com usuario valido e senha em branco
    When Clico em login
    Then Mensagem senha obrigatorio devera sera exibida