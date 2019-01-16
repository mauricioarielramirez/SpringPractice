package ar.com.taskmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

import java.text.SimpleDateFormat;

/**
 * Created by ArielRamirez on 8/1/2019.
 */
public class JsonConverter implements ResponseTransformer {

    @Override
    public String render(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(dateFormat);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
