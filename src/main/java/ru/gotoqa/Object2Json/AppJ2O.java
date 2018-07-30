package ru.gotoqa.Object2Json;

import org.codehaus.jackson.map.ObjectMapper;
import ru.gotoqa.Object2Json.models.MyPojoJ2O;

import java.io.IOException;

//https://jsonlint.com/ JSONLint is a validator

/**
 * @author Muflikhunov Roman
 */

public class AppJ2O {
    public static void main(String[]args) {

        ObjectMapper mapper = new ObjectMapper();
        MyPojoJ2O myPojoJ2O = createModel();

        try {
            String jsonString = mapper.writeValueAsString(myPojoJ2O);
            System.out.println(jsonString);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static MyPojoJ2O createModel() {
        MyPojoJ2O myPojoJ2O = new MyPojoJ2O();
        myPojoJ2O.setId(1);
        myPojoJ2O.setName("A green door");
        myPojoJ2O.setPrice(12.50f);

        String tags[] = {"home", "green"};
        myPojoJ2O.setTags(tags);

        return myPojoJ2O;
    }
}
