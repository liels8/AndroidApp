pipeline {
    stages {
        stage("Checkout") {
            steps {
                 git update-index --chmod=+x gradlew
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
