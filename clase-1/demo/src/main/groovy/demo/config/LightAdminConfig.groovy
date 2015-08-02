package demo.config

import org.lightadmin.api.config.LightAdmin
import org.lightadmin.core.config.LightAdminWebApplicationInitializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.servlet.ServletContext
import javax.servlet.ServletException
/**
 * Created by domix on 01/08/15.
 */
@Configuration
class LightAdminConfig {
  @Bean
  ServletContextInitializer servletContextInitializer() {
    new ServletContextInitializer() {
      @Override
      void onStartup(ServletContext servletContext) throws ServletException {
        LightAdmin.configure(servletContext)
          .basePackage('demo.admin')
        .baseUrl('/crud')
          .security(false)


        new LightAdminWebApplicationInitializer().onStartup(servletContext)
      }
    }
  }
}
