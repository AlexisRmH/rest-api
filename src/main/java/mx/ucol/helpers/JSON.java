package mx.ucol.helpers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.ucol.models.ToDo;

public class JSON {
    public static ToDo jsonToObject(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ToDo toDo = mapper.readValue(jsonString, ToDo.class);

        return toDo;
    }

    public static String objectToJson(ToDo todo) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(todo);

        return json;
    }

    public static String objectToJson(List<ToDo> toDoList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(toDoList);

        return json;
    }
}
