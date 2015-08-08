package demo.web

import demo.service.GitHubService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by domix on 01/08/15.
 */
@Controller
class HomeController {
  @Autowired
  GitHubService gitHubService

  @RequestMapping('/')
  @ResponseBody
  String index() {

    String name = 'chochos'
    "hola: ${name}"
  }

  @RequestMapping('/{user}')
  @ResponseBody
  String getgitHunIno(@PathVariable('user') String user) {
    gitHubService.doWithJavanica(user)
  }

  @RequestMapping('/plain/{user}')
  @ResponseBody
  String getgitHunInos(@PathVariable('user') String user) {
    gitHubService.doWithoutJavanica(user)
  }

  @RequestMapping('/hystrix/{user}')
  @ResponseBody
  String getgitHunInoss(@PathVariable('user') String user) {
    gitHubService.doWithARealHystrixCommand(user)
  }
}
