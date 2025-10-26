pipeline {
    agent any
    tools {
        maven 'Maven3'
    }
    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'leevijr/shoppingcart'
        DOCKER_IMAGE_TAG = 'latest'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/leevi-rinnetmaki/OTP2_Week2_in_class_assignment.git'
            }
        }
        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}")
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}