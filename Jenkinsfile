import groovy.json.JsonOutput

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
                script {
                    filesTop = sh(returnStdout: true, script: "find / -type f -exec du -a {} + 2>/dev/null | sort -n -r | head -n ${params.number_of_files}")
                    filesTop = filesTop.trim().split("\n").collect { it.split("\t")[1] }
                }
            }
        }
        stage('write result') {
            steps {
                script {
                    if (params.result_format == "TXT") {
                        echo filesTop.inject('') { str, item -> str + "${item}\n" }
                    }
                    if (params.result_format == "JSON") {
                        echo '[' + filesTop.inject {str, item -> str + "${item}"} + ']'
                    }
                    if (params.result_format == "YML") {
                        echo filesTop.inject('fileNames:\n') { str, item -> str + "\t- ${item}\n" }
                    }
                }
            }
        }
        stage('git push') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
