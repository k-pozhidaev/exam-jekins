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
                    echo filesTop.trim()
                }
            }
        }
        stage('write result') {
            steps {
                script {
                    if (params.result_format == "TXT") {
                        final StringBuilder sb = new StringBuilder()
                        names.each {sb.append("${it}\n")}
                        echo sb.toString()
                    }
                    if (params.result_format == "JSON") {
                        echo JsonOutput.toJson(filesTop)
                    }
                    if (params.result_format == "YML") {
                        final StringBuilder sb = new StringBuilder('fileNames:\n')
                        names.each { sb.append("\t- ${it}\n") }
                        echo sb.toString()
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
