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
                if (params.result_format == "TXT") {
                    echo list_to_plain_text(filesTop)
                }
                if (params.result_format == "JSON") {
                    echo JsonOutput.toJson(filesTop)
                }
                if (params.result_format == "YML") {
                    echo list_to_yaml_string(filesTop)
                }
                echo filesTop.getClass().toString()
            }
        }
        stage('git push') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}


def list_to_yaml_string(List<String> names, Closure c) {
    final StringBuilder sb = new StringBuilder('fileNames:\n')
    names.each { sb.append("\t- ${it}\n") }
    return sb.toString()
}

def list_to_plain_text(List<String> names, Closure c) {
    final StringBuilder sb = new StringBuilder()
    names.each {sb.append("${it}\n")}
    return sb.toString()
}
