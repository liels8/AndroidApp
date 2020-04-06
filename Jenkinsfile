pipeline {
    agent any
        tools {
        jdk 'jdk8' 
    }
    stages {
        stage("Checkout") {
            steps {
                git url: 'https://github.com/liels9/NewTeam3'
            }
        }
        stage("Compile") {
            steps {
                sh 'chmod 755 ./gradlew'
                sh "./gradlew build"
            }
        }
    }
}
