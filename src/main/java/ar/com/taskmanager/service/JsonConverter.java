package ar.com.taskmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

/**
 * Created by ArielRamirez on 8/1/2019.
 */
public class JsonConverter implements ResponseTransformer {

    @Override
    public String render(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
