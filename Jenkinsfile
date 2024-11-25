pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo/microservices-app.git'
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
                    sh 'docker build -t <ECR_REPO>/product-service:latest .'
                }
                dir('user-service') {
                    sh 'docker build -t <ECR_REPO>/user-service:latest .'
                }
            }
        }
        stage('Push to ECR') {
            steps {
                sh 'aws ecr get-login-password --region <AWS_REGION> | docker login --username AWS --password-stdin <ECR_REPO>'
                sh 'docker push <ECR_REPO>/product-service:latest'
                sh 'docker push <ECR_REPO>/user-service:latest'
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
