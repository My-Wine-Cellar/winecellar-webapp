pipeline {

    agent any

    triggers {
        pollSCM('')
    }

    stages {
        stage('Clean & Compile') {
            steps {
                gradlew('clean', 'classes')
            }
        }
        stage('Unit Tests') {
            steps {
                gradlew('test')
            }
        }

        stage('Assemble') {
            steps {
                gradlew('assemble')
                stash includes: '**/build/libs/*.jar', name: 'winecellar-webapp'
            }
        }
        stage('Promotion') {
            steps {
                timeout(time: 1, unit:'DAYS') {
                    input 'Deploy to Production?'
                }
            }
        }
        stage('Staging') {
            steps {
               sh "echo 'not ready ' "
            }
        }
    }
    post {
        failure {
            mail to: 'pauldarlingtonpearson@gmail.com', subject: 'Build failed', body: 'Please fix!'
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}