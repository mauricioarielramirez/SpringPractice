package ar.com.taskmanager.application;

import ar.com.taskmanager.customException.CustomException;
import ar.com.taskmanager.model.domain.Task;
import ar.com.taskmanager.model.dto.IdentifierDTO;
import ar.com.taskmanager.service.JsonConverter;
import ar.com.taskmanager.service.TaskService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

import static spark.Spark.*;

/**
 * Created by ArielRamirez on 7/1/2019.
 */
public class TaskApplication {
    private TaskService taskService;

    /**
     * En el constructor voy a "instanciar" el servicio vinculado con la mini app y llamo al método
     * para que se establezcan las rutas posibles para task
     * @param taskService Inyección de la dependencia
     */
    public TaskApplication(final TaskService taskService) {
        this.taskService = taskService;
        setRoutes();
    }

    /**
     * En este método voy a establecer las rutas para lo que tenga que ver con esta entidad
     */
    public void setRoutes() {
        port(8080);

        get("/task/get/:id", (req,res)->{
            try{
                res.type("application/json");
                Long id = Long.valueOf(req.params("id"));
                return taskService.getEntity(id);
            }catch (CustomException ex) {
                return  ex.getElements();
            }
        },new JsonConverter());

        post("/task",(req,res)->{
            res.type("application/json");
            try {
                return taskService.addEntity(readBody(req.body()));
            } catch (CustomException ex) {
                return ex.getElements();
            }
        },new JsonConverter());

        /**
         * Voy a utilizar PUT para modificar
         */
        put("/task",(req,res)->{
            res.type("application/json");
            try {
                return taskService.modifyEntity(readBody(req.body()));
            }catch (CustomException ex) {
                return ex.getElements();
            }
        }, new JsonConverter());

        delete("/task",(req,res)->{
            res.type("application/json");
            try {
                return taskService.deleteEntity(readBody(req.body()));
            }catch (CustomException ex) {
                return  ex.getElements();
            }
        },new JsonConverter());

        //Voy a usar esta ruta para hacer las vinculaciones
        post("/task/link",(req,res)->{
            return taskService.linkUserToTask(Application.readBodyLink(req.body()),TaskService.LINK);
        },new JsonConverter());

        //y este para desvincular
        post("/task/unlink",(req,res)->{
            return taskService.linkUserToTask(Application.readBodyLink(req.body()),TaskService.UNLINK);
        },new JsonConverter());


        get("/task/list/:last/:range",(req,res)->{
            try{
                res.type("application/json");
                Integer last = Integer.valueOf(req.params("last"));
                Integer range = Integer.valueOf(req.params("range"));
                return taskService.listAll(last,range);
            }catch (CustomException ex) {
                return  ex.getElements();
            }
        },new JsonConverter());

        get("/task/listByExample/:last:range",(req,res)->{
            res.type("application/json");
            Integer last = Integer.valueOf(req.params("last"));
            Integer range = Integer.valueOf(req.params("range"));
            return  taskService.listByExample(readBody(req.body()),last,range);
        },new JsonConverter());

        get("/task/listLikeByExample/:last:range",(req,res)->{
            res.type("application/json");
            Integer last = Integer.valueOf(req.params("last"));
            Integer range = Integer.valueOf(req.params("range"));
            return  taskService.listLikeByExample(readBody(req.body()),last,range);
        },new JsonConverter());

    }

    /**
     * Convertimos en objeto el json, sino lanzo la excepción pero lo controlo con mi obj
     * @param jsonData
     * @return
     * @throws Exception
     */
    private Task readBody(final String jsonData) throws CustomException {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonData, Task.class);
        } catch (Exception ex) {
            CustomException customException = new CustomException();
            customException.addMessage(CustomException.STACK_TRACE, ex.getMessage());
            customException.addMessage(CustomException.ERROR_TAG, CustomException.GENERAL_MESSAGE);
        }
        return null;
    }


    }
