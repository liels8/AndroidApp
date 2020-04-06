pipeline {
    agent any
    stages {
        stage("Checkout") {
            steps {
                 sh 'chmod 755 ./gradlew'
                 git url: 'https://github.com/liels9/NewTeam3'
            }
        }
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
    }
}
