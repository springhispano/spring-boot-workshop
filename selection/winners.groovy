@Grapes( [
  @Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.2'),
  @Grab(group='com.jakewharton.fliptables', module='fliptables', version='1.0.2') 
])
import wslite.rest.RESTClient
import wslite.rest.Response
import groovy.json.JsonSlurper
import com.jakewharton.fliptables.FlipTableConverters

if (!args) {
  println "Debes proporcionar tu token de github"
  System.exit(-1)
}
String token = args[0]

RESTClient client = new RESTClient('https://api.github.com')
Map request = [
  path: '/repos/springhispano/spring-boot-workshop/issues', 
  headers: ["Authorization": "token ${token}"],
  query: [labels: 'request for registration']
]
Response response = client.get(request)

List issues = new JsonSlurper().parseText(response.contentAsString)

def winners = issues.collect { issue ->
  [
    number: issue.number, 
    created_at: issue.created_at, 
    user: issue.user.login, 
    labels: issue.labels.findAll { label -> label.name.endsWith('_vote') }.collect { label -> [name: label.name] }
  ]
}.sort { issue ->
  issue.labels.size()
}.reverse().take(7)

String[] tHeaders = ['User', 'IssueId', '# Votes'].toArray()
String[][] data = winners.collect {
  [it.user, it.number.toString(), it.labels.size().toString()].toArray()
}.toArray()

println FlipTableConverters.fromObjects(tHeaders, data)