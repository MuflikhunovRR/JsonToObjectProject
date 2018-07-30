package ru.gotoqa.Json2Object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.*;
import ru.gotoqa.Json2Object.models.MyPojo;

import java.io.File;
import java.io.IOException;

/**
 * @author Muflikhunov Roman
 */

public class App {
    public static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[]arg){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = "{\n" +
                    "  \"id\": 1,\n" +
                    "  \"name\": \"A green door\",\n" +
                    "  \"price\": 12.50,\n" +
                    "  \"tags\": [\"home\", \"green\"]\n" +
                    "}";

            //1 from file
            MyPojo myPojo = mapper.readValue(new File("D:\\JAVA\\Java_SRC\\Json2Object\\src\\main\\resources\\simpleSample.json"), MyPojo.class);
            LOGGER.info(myPojo.getId());
            LOGGER.info(myPojo.getName());
            LOGGER.info(myPojo.getTags()[1]);

            //2 from String
            MyPojo myPojo2 = mapper.readValue(jsonString, MyPojo.class);
            LOGGER.info("--------------------------");
            LOGGER.info(myPojo2.getTags()[1]);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
