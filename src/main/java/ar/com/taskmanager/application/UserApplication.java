package ar.com.taskmanager.application;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.model.domain.Task;
import ar.com.taskmanager.model.domain.User;
import ar.com.taskmanager.service.JsonConverter;
import ar.com.taskmanager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static spark.Spark.*;

/**
 * Created by ArielRamirez on 9/1/2019.
 */
public class UserApplication {
    private UserService userService;

    @Autowired
    public UserApplication(final UserService userService) {
        this.userService = userService;
        setRoutes();
    }

    public void  setRoutes() {
        get("/user/:id", (req,res)->{
            try{
                res.type("application/json");
                Long id = Long.valueOf(req.params("id"));
                return userService.getEntity(id);
            }catch (CustomException ex) {
                return  ex.getElements();
            }
        },new JsonConverter());

        post("/user",(req,res)->{
            res.type("application/json");
            try {
                return userService.addEntity(readBody(req.body()));
            } catch (CustomException ex) {
                return ex.getElements();
            }
        },new JsonConverter());

        /**
         * Voy a utilizar PUT para modificar
         */
        put("/user",(req,res)->{
            res.type("application/json");
            try {
                return userService.modifyEntity(readBody(req.body()));
            }catch (CustomException ex) {
                return ex.getElements();
            }
        }, new JsonConverter());

        delete("/user",(req,res)->{
            res.type("application/json");
            try {
                return userService.deleteEntity(readBody(req.body()));
            }catch (CustomException ex) {
                return  ex.getElements();
            }
        },new JsonConverter());

    }

    /**
     * Convertimos en objeto el json, sino lanzo la excepción pero lo controlo con mi implementación de exception
     * @param jsonData
     * @return
     * @throws Exception
     */
    private User readBody(final String jsonData) throws CustomException {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonData, User.class);
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
        }
        return null;
    }

}
