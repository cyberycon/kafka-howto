 pipeline {
     agent any
     stages {
         stage ('build') {
             agent {
                 docker {
                     image 'maven:3.8.1-adoptopenjdk-8'
                     reuseNode true
                 }
             }

             steps {
                 echo 'Hello world!'
                 sh 'cd 02-embedded-kafka;mvn package'
             }
         }
     }
 }
