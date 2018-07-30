package ru.gotoqa.Object2Json;

import org.codehaus.jackson.map.ObjectMapper;
import ru.gotoqa.Object2Json.models.*;

import java.io.IOException;

/**
 * @author Muflikhunov Roman
 */

public class AppAdvanced {
    public static void main(String [] args){
        ObjectMapper mapper = new ObjectMapper();
        MyPojoAdvanced pojoAdvanced = createModel();

        try {
            String jsonString = mapper.writeValueAsString(pojoAdvanced);
            System.out.println(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static MyPojoAdvanced createModel() {
        MyPojoAdvanced pojoAdvanced = new MyPojoAdvanced();
        pojoAdvanced.set$schema("http://json-schema.org");
        pojoAdvanced.setTitle("Product");
        pojoAdvanced.setDescription("A product from Acme's");
        pojoAdvanced.setType("object");


        //set ID
        Id id = new Id();
        id.setDescription("The unique identifier for a product");
        id.setType("integer");

        //set Name
        Name name = new Name();
        name.setDescription("Name of the product");
        name.setType("string");

        //set Price
        Price price = new Price();
        price.setType("number");
        price.setMinimum(0);
        price.setExclusiveMinimum(true);

        //set Tags
        Tags tags = new Tags();
        tags.setType("array");

        Items items = new Items();
        items.setType("string");

        tags.setItems(items);
        tags.setMinItems(1);
        tags.setUniqueItems(true);


        Properties properties = new Properties();
        properties.setId(id);
        properties.setName(name);
        properties.setPrice(price);
        properties.setTags(tags);

        pojoAdvanced.setProperties(properties);


        String required [] = {"id", "name", "price"};
        pojoAdvanced.setRequired(required);

        return pojoAdvanced;

    }
}
