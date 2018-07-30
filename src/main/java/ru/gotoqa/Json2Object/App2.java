package ru.gotoqa.Json2Object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.gotoqa.Json2Object.models.MyPojo2;

import java.io.File;
import java.io.IOException;

/**
 * @author Muflikhunov Roman
 */

public class App2 {
    public static final Logger LOGGER = LogManager.getLogger(App2.class);

    public static void main(String[]arg){
        ObjectMapper mapper = new ObjectMapper();
        try {
            MyPojo2[] myPojo2 = mapper.readValue(new File(
                    "D:\\JAVA\\Java_SRC\\Json2Object\\src\\main\\resources\\advancedSample.json"), MyPojo2[].class);

            //1 way
            LOGGER.info(myPojo2[1].getLast_name());
            LOGGER.info("------------------");

            //2 way loop
            for(MyPojo2 pojo2 : myPojo2){
                LOGGER.info(pojo2.getFirst_name()+ " " + pojo2.getLast_name()+ ". " + "Email: " +pojo2.getEmail());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
