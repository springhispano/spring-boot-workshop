package demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.EnableHystrix

@SpringBootApplication
@EnableHystrix
class DemoApplication {

  static void main(String[] args) {
    SpringApplication.run DemoApplication, args
  }
}
