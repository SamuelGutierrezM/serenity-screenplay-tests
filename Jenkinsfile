pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
    }

    triggers {
        // Revisa GitHub cada 5 minutos y ejecuta si hay commits nuevos
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Pruebas Serenity') {
            steps {
                sh 'mvn -B clean verify -Dheadless.mode=true'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/failsafe-reports/TEST-*.xml', allowEmptyResults: true
            archiveArtifacts artifacts: 'target/site/serenity/**', allowEmptyArchive: true
        }
    }
}