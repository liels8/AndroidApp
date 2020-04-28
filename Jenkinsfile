pipeline { 
  agent { 
    docker { 
      image 'windsekirun/jenkins-android-docker:1.1.1' 
    } 
  } 
  options { 
    // Stop the build early in case of compile or test failures 
    skipStagesAfterUnstable() 
  } 
  stages { 
    stage ('Prepare'){ 
      steps { 
        sh 'chmod +x ./gradlew'
        sh 'adb shell monkey -p your.com.example.newproj -v 500'
      } 
    } 
    stage('Compile') { 
      steps { 
        sh 'ls -l'
        // Compile the app and its dependencies 
        sh './gradlew compileDebugSources' 
      } 
    } 
    stage('Build APK') { 
      steps { 
        // Finish building and packaging the APK 
        sh './gradlew assembleDebug' 
      } 
    }
     stage('UnitTests') {
      //Start all the existing tests in the test package 
          steps { 
            sh './gradlew test --rerun-tasks'
            sh './gradlew connectedAndroidTest'

      }         
  }
  } 
}
