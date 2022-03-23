pipeline {
  environment {
       registry = 'javasre2022/postservice'
       dockerHubCreds = 'docker_hub'
       dockerImage = ''
    }
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
              docker.withRegistry('', dockerHubCreds) {
                dockerImage.push("$currentBuild.number")
                dockerImage.push("latest")
              }
            }
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
         stage('Wait for approval') {
        when {
            branch 'main'
        }
        steps {
            script {
                try {
                    timeout(time: 5, unit: 'MINUTES') {
                        approved = input message: 'Deploy to production?', ok: 'Continue',
                                           parameters: [choice(name: 'approved', choices: 'Yes\nNo', description: 'Deploy build to production')]
                        if(approved != 'Yes') {
                            error('Build did not pass approval')
                        }
                    }
                } catch(error) {
                    error('Build failed because timeout was exceeded')
                }
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
