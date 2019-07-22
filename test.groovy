import groovy.json.JsonOutput


a="""75568\t/usr/share/jenkins/jenkins.war
63528\t/usr/local/openjdk-8/jre/lib/rt.jar
50800\t/usr/local/openjdk-8/src.zip
17908\t/usr/local/openjdk-8/lib/tools.jar
17384\t/usr/local/openjdk-8/lib/ct.sym
"""
//a.split("\n").each { println it.split("\t")[1] }
names = a.split("\n").collect { it.split("\t")[1] }
json = JsonOutput.toJson(names)

println 'JSON'
println json
println 'YAML'
println listToYamlString(names)
println 'TEXT'
println listToPlainText(names)
new File("result").write("")

//Stream.of(a.split("\n"))

def static listToYamlString(List<String> names) {
    final StringBuilder sb = new StringBuilder('fileNames:\n')
    names.each { sb.append("\t- ${it}\n") }
    return sb.toString()
}

def static listToPlainText(List<String> names){
    final StringBuilder sb = new StringBuilder()
    names.each {sb.append("${it}\n")}
    return sb.toString()
}