package ar.com.taskmanager.application;

import ar.com.taskmanager.model.domain.User;
import ar.com.taskmanager.service.TaskService;
import ar.com.taskmanager.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan({"ar.com.taskmanager.*"})
public class Application {

    //Acá debo arrancar mi aplicación. Le voy a indicar a Spring que lea la configuración desde un xml
    //Además voy a llamar a otro objeto que se va a encargar de inicilizar los servicios necesarios para la app
    public static void main (String args[]) {
        //Aca indico que vaya a leer el xml

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springConfiguration.xml");

        new TaskApplication(applicationContext.getBean(TaskService.class));
        new UserApplication(applicationContext.getBean(UserService.class));

    }
}
