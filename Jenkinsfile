pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Guberapandian/microservices-app.git']])
            }
        }
        stage('Build Microservices') {
            steps {
                dir('product-service') {
                    sh 'mvn clean package'
                }
                dir('user-service') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build Docker Images') {
            steps {
                dir('product-service') {
                    sh 'docker build -t 762233769537.dkr.ecr.ap-southeast-1.amazonaws.com/micro/product-service:latest .'
                }
                dir('user-service') {
                    sh 'docker build -t 762233769537.dkr.ecr.ap-southeast-1.amazonaws.com/micro/user-service:latest .'
                }
            }
        }
        stage('Push to ECR') {
            steps {
                sh 'aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 762233769537.dkr.ecr.ap-southeast-1.amazonaws.com'
                sh 'docker push 762233769537.dkr.ecr.ap-southeast-1.amazonaws.com/micro/product-service:latest'
                sh 'docker push 762233769537.dkr.ecr.ap-southeast-1.amazonaws.com/micro/user-service:latest'
            }
        }
        stage('Deploy to EKS') {
            steps {
                sh 'kubectl apply -f deploy/eks-deployment.yml'
                sh 'kubectl apply -f deploy/eks-service.yml'
            }
        }
    }
}
