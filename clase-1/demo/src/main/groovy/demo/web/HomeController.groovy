package demo.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by domix on 01/08/15.
 */
@Controller
class HomeController {

  @RequestMapping('/')
  @ResponseBody
  String index() {

    String name = 'chochos'
    "hola: ${name}"
  }
}
