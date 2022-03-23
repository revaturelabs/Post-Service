pipeline {
  agent any
  stages {
       stage('Test') {
         steps {
           sh 'mvn -f pom.xml test'
        }
       }

       stage('Build') {
         steps {
            sh 'mvn -f pom.xml clean install'
            sh 'mvn -f pom.xml clean package -DskipTests'
         }
       }

        stage('Docker Hub') {
          steps {
            script {
            sh "docker build -t javasre2022/postservice ."
            }
          }
        }
        
        stage('Docker Deliver') {
        steps {
            script {
              sh "docker login -u javaSRE2022 -p 7ce357ae-b369-4a7d-876c-10d27cf1171e"
              sh "docker push javasre2022/postservice:latest"
            }
        }
    }

        stage('SRE Approval') {
          steps {
            sh 'mvn --version'
          }
        }

         stage('Checkout') {
          steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'ghp', url: 'https://github.com/revaturelabs/Post-Service']]])
          }
        }

        stage('Quality Gate1') {
          steps {    
                 script {
                    sh 'mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=Post-service \
                    -Dsonar.host.url=http://35.222.177.228:9000 \
                    -Dsonar.login=45499a6ff15b3dbf936db9278fc14e43ea283295'
                 } 
           } 
        }

        stage('DeployToCluster') {
          steps {
            withKubeConfig(credentialsId: '39a085b6-0856-43e2-94c5-7c1e4b583506', serverUrl: 'https://35.232.148.254') {
              sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'
              sh 'chmod u+x ./kubectl'
              sh './kubectl get pods'
              sh './kubectl apply -f Kubernetes/deployment.yml'

            }

          }
        }
  }
}
