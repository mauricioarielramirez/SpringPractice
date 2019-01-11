package ar.com.taskmanager.model.dto;

import java.util.HashMap;

/**
 * Created by ArielRamirez on 10/1/2019.
 * La voy a usar para pasar identificadores a modo de mapeo de n cantidades
 */
public class IdentifierDTO {
    private HashMap<String,String> mapping;

    public IdentifierDTO() {
        mapping = new HashMap<String,String>();
    }

    public HashMap<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(HashMap<String, String> mapping) {
        this.mapping = mapping;
    }
}
