pipeline {
    agent any
//number_of_files
//result_format
    parameters {
        string(name: 'number_of_files', defaultValue: '5', description: 'Number of files?')
        choice(name: 'result_format', choices: ['TXT', 'JSON', 'YML'], description: 'Format')

    }

//    du -a / 2>/dev/null | sort -n -r | head -n 20
    stages {
        stage('read parameters') {
            steps {
                echo "Number of files: ${params.number_of_files}"
                echo "Format: ${params.result_format}"
            }
        }
        stage('find top biggest') {
            steps {
                echo 'Testing..'

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
