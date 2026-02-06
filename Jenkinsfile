pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'your-registry.example.com'
        DOCKER_IMAGE    = "${DOCKER_REGISTRY}/spring-boot-microservice"
        DOCKER_TAG      = "${env.BUILD_NUMBER}"
        DOCKER_CREDS    = credentials('docker-registry-credentials')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Docker Push') {
            steps {
                sh "echo ${DOCKER_CREDS_PSW} | docker login ${DOCKER_REGISTRY} -u ${DOCKER_CREDS_USR} --password-stdin"
                sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                sh "docker push ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh "kubectl apply -f k8s/deployment.yaml"
                sh "kubectl apply -f k8s/service.yaml"
                sh "kubectl rollout status deployment/spring-boot-microservice"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
