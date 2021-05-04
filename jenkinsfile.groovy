pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'develop', credentialsId: 'MyGithubConnection', url: 'https://github.com/mskyrim/butterfly.git'
            }

        }
        stage('Build') {
            steps {
                sh "cd reviewer && ./gradlew clean build"

            }

            post {
                success {
                    echo 'Build successful'
                }
            }
        }
    }
}
