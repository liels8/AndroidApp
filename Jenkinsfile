pipeline {
    agent any
    stages {
        stage("Checkout") {
            steps {
                git url: https://github.com/liels9/NewTeam3
            }
        }
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
    }
}
