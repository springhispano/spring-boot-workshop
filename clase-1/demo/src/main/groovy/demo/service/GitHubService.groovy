package demo.service

import com.netflix.hystrix.HystrixCommand
import com.netflix.hystrix.HystrixCommandGroupKey
import org.springframework.stereotype.Component
import wslite.rest.RESTClient

/**
 * Created by domix on 08/08/15.
 */
@Component
class GitHubService {


  String doProcess() {
    def command = new MyHystrixCommand()
    //Sincrona, bloqueante
    command.execute()

    def future = command.queue()

    future.get()


    def observable = command.observe()


    observable.doOnCompleted()

  }

  String doWithARealHystrixCommand(String user) {
    new MyHystrixCommand(user).execute()
  }

  @com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand(fallbackMethod = 'doo')
  String doWithJavanica(String user) {
    new RESTClient("https://api.github.com/users/${user}/orgs")
      .get().contentAsString
  }

  String doWithoutJavanica(String user) {
    new RESTClient("https://api.github.com/users/${user}/orgs")
      .get().contentAsString
  }


  String doo(String foo) {
    'este es el fallback'
  }
}

class MyHystrixCommand extends HystrixCommand<String> {

  private final String user

  public MyHystrixCommand(String user) {
    super(HystrixCommand.Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey('algo')))
    this.user = user
  }


  @Override
  protected String run() throws Exception {
    new RESTClient("https://api.github.com/users/${user}/orgs")
      .get().contentAsString
  }

  protected String getFallback() {
    'este es el fallback'
  }

}