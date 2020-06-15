pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven 'maven'
      jdk 'java11' 

   }

   stages {
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/francois-dorval/SpoilGotApp.git'

            // Run Maven on a Unix agent.
            sh "mvn  package"
         }

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
               archiveArtifacts 'target/*.war'
            }
         }
      }
   }
}
