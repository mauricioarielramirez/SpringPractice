package ar.com.taskmanager.application;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.model.dto.IdentifierDTO;
import ar.com.taskmanager.service.TaskService;
import ar.com.taskmanager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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

    /**
     * Este método lo dejo disponible para todos los servicios, ya que es un map de clave-valor para id
     * @param jsonData
     * @return
     * @throws CustomException
     */
    public static IdentifierDTO readBodyLink(final String jsonData) throws CustomException {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonData, IdentifierDTO.class);
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
        }
        return null;
    }

}
