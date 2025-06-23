pipeline {
    agent any
    environment {
        CORE_CREDENTIALS = credentials('CORE_CREDENTIALS')
    }
    triggers {
        cron('H 9,12,15,18 * * 1-5') // Executa nos dias úteis às 9h, 12h, 15h e 18h
    }
    stages {
        stage('Configurar Credenciais') {
            steps {
                script {
                    writeFile(file: 'credentials.properties', text: """\
                                       coreUsername=${CORE_CREDENTIALS_USR}
                                       corePassword=${CORE_CREDENTIALS_PSW}
                                       """.stripIndent())
                }
            }
        }
        stage('Usar Credenciais') {
            steps {
                bat 'type credentials.properties'
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    bat 'mvn test -Dcucumber.options="--tags @given,@when,@then"'
                }
            }
        }
    }
}
