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
      }
       def emailTestReport = ""
        post {
                    always {
                        junit 'tests.xml'

                        script {
                            AbstractTestResultAction testResultAction =  currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
                            if (testResultAction != null) {
                                def totalNumberOfTests = testResultAction.totalCount
                                def failedNumberOfTests = testResultAction.failCount
                                def failedDiff = testResultAction.failureDiffString
                                def skippedNumberOfTests = testResultAction.skipCount
                                def passedNumberOfTests = totalNumberOfTests - failedNumberOfTests - skippedNumberOfTests
                                emailTestReport = "Tests Report:\n Passed: ${passedNumberOfTests}; Failed: ${failedNumberOfTests} ${failedDiff}; Skipped: ${skippedNumberOfTests}  out of ${totalNumberOfTests} "
                            }
                        }

                        mail to: 'lielsananes8@gmail.com',
                        subject: "Tests are finished: ${currentBuild.fullDisplayName}",
                        body: "Tests are finished  ${env.BUILD_URL}\n  Test Report: ${emailTestReport} "
                    }

    }
  }
      post {
    always {
       mail to: 'lielsananes8@gmail.com',
          subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
         body: "${env.BUILD_URL} has result ${currentBuild.result}"
    }
  }
}
