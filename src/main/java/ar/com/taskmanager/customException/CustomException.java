package ar.com.taskmanager.customException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ArielRamirez on 9/1/2019.
 * Creo una clase personalizada para manejarme más fácil con las excepciones
 */
public class CustomException extends Exception {
    private Map elements;

    public static final String GENERAL_MESSAGE = "Algo ha salido mal";
    public static final String ERROR_TAG = "Error";
    public static final String STACK_TRACE = "StackTrace";
    public static final String SIMPLE_MESSAGE_TO_LOG = "Simple message to log";
    public static final String SIMPLE_MESSAGE_TO_HUMAN = "Simple message to human";

    public CustomException() {
        super();
        elements = new HashMap<String,String>();
    }

    public void addMessage(String value1, String value2) {
        elements.put(value1,value2);
    }

    public CustomException(HashMap<String,String> elements) {
        super();
        this.elements = elements;
    }

    public Map getElements() {
        return elements;
    }

}
