pipeline {
    agent any

    parameters {
        string(name: 'number_of_files', defaultValue: '5', description: 'Number of files?')
        choice(name: 'result_format', choices: ['TXT', 'JSON', 'YML'], description: 'Format')

    }

    stages {
        stage('read parameters') {
            steps {
                echo "Number of files: ${params.number_of_files}"
                echo "Format: ${params.result_format}"
            }
        }
        stage('find top biggest') {
            steps {
                sh(returnStdout: true, script: "find / -type f -exec du -a {} + 2>/dev/null | sort -n -r | head -n ${params.number_of_files}")
            }
        }
        stage('write result') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('git push') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
