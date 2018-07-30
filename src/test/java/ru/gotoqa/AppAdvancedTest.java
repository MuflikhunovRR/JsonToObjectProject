package ru.gotoqa;

/**
 * @author Muflikhunov Roman
 */

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.*;
import ru.gotoqa.Json2Object.models.MyPojo2;
import ru.gotoqa.Object2Json.models.MyPojoAdvanced;

import java.io.File;
import java.io.IOException;

import static ru.gotoqa.Object2Json.AppAdvanced.createModel;

public class AppAdvancedTest {

    private static String jsonOrig = "{\"$schema\":\"http://json-schema.org\",\"title\":\"Product\",\"description\":\"A product from Acme's\",\"type\":\"object\",\"properties\":{\"id\":{\"description\":\"The unique identifier for a product\",\"type\":\"integer\"},\"name\":{\"description\":\"Name of the product\",\"type\":\"string\"},\"price\":{\"type\":\"number\",\"minimum\":0,\"exclusiveMinimum\":true},\"tags\":{\"type\":\"array\",\"items\":{\"type\":\"string\"},\"minItems\":1,\"uniqueItems\":true}},\"required\":[\"id\",\"name\",\"price\"]}";
    private static String jsonOrig2 = "{\"example\" : \"any { [ : } ] words\" { [ : } ]}\n";
    private static MyPojo2 [] myPojo2;
    private static ObjectMapper mapper;


    @BeforeAll
    static void init() throws IOException {
        mapper = new ObjectMapper();
        myPojo2 = mapper.readValue(new File(
                "D:\\JAVA\\Java_SRC\\Json2Object\\src\\main\\resources\\advancedSample.json"), MyPojo2[].class);
    }

    @RepeatedTest(3)
    @DisplayName("Test compare string & file Json")
    @Test
    void test() throws IOException {
        MyPojoAdvanced pojoAdvanced = createModel();
        String jsonString = mapper.writeValueAsString(pojoAdvanced);
        Assertions.assertEquals(jsonOrig, jsonString, "Incorrect json");
        System.out.println(jsonString);
    }


    @DisplayName("Test validation Json")
    @Test
    void test2() throws IOException {
        MyPojoAdvanced pojoAdvanced = createModel();
        String jsonString = mapper.writeValueAsString(pojoAdvanced);
        Assertions.assertEquals(jsonOrig, jsonString, "Incorrect json");
        System.out.println(jsonString);


        try{
            JsonParser parser = new JsonParser();
            parser.parse(jsonOrig2);
            System.out.println("Ok valid Json String:");

        }
        catch(JsonSyntaxException jse){
            System.out.println("Not a valid Json String1:"+jse.getMessage());
        }
    }


}