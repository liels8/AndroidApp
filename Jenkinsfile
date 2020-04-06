pipeline {
    stages {
        stage("Checkout") {
            steps {
                 git url: 'https://github.com/liels9/NewTeam3'
            }
        }
        stage("Compile") {
            steps {
                sh "chmod +x gradlew"
                sh "./gradlew compileJava"
            }
        }
    }
}
