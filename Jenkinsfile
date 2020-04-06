pipeline {
    agent any
    stages {
        stage('somestage') {
            steps {
                script {
                    def version = sh (
                        script: "./gradlew properties -q | grep \"version:\" | awk '{print \$2}'",
                        returnStdout: true
                    ).trim()
                    sh "echo Building project in version: $version"

                }
            }
        }
    }
}
