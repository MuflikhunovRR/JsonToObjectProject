package ru.gotoqa;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.gotoqa.Json2Object.models.MyPojo2;
import ru.gotoqa.JsonGenerateClass.UserTest;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;


/**
 * @author Muflikhunov Roman
 */

public class Json2ObjectDataBaseTest {
    public static final Logger LOGGER = LogManager.getLogger(UserTest.class);


    private static String jsonOrig = "{\"$schema\":\"http://json-schema.org\",\"title\":\"Product\",\"description\":\"A product from Acme's\",\"type\":\"object\",\"properties\":{\"id\":{\"description\":\"The unique identifier for a product\",\"type\":\"integer\"},\"name\":{\"description\":\"Name of the product\",\"type\":\"string\"},\"price\":{\"type\":\"number\",\"minimum\":0,\"exclusiveMinimum\":true},\"tags\":{\"type\":\"array\",\"items\":{\"type\":\"string\"},\"minItems\":1,\"uniqueItems\":true}},\"required\":[\"id\",\"name\",\"price\"]}";
    private static String jsonIcorrect = "{\"example\" : \"any { [ : } ] words\" { [ : } ]}\n";
    private static MyPojo2[] myPojo2;
    private static ClassPathXmlApplicationContext contextdb;
    private static NamedParameterJdbcTemplate nquOracle;

    @BeforeAll
    static void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        myPojo2 = mapper.readValue(new File(
                "D:\\JAVA\\Java_SRC\\Json2Object\\src\\main\\resources\\advancedSample.json"), MyPojo2[].class);

        contextdb = new ClassPathXmlApplicationContext("db.xml");
        nquOracle = new NamedParameterJdbcTemplate(contextdb.getBean(DataSource.class));
    }

    //@RepeatedTest(1)
    @DisplayName("Test. Json no name")
    @Test
    void testMain() {
        int id = 1;
        HashMap<String, Object> param = new HashMap<>();
        param.put("ID", id);
        List<MyPojo2> myPojo2s = nquOracle.query("SELECT * FROM PERSON WHERE ID = :ID",
                param,
                new BeanPropertyRowMapper<>(MyPojo2.class));

        List<MyPojo2> newDatasetAirs1 = nquOracle.query("SELECT * FROM PERSON",
                new BeanPropertyRowMapper<>(MyPojo2.class));
        for(MyPojo2 pojo2 : newDatasetAirs1){
            LOGGER.info(pojo2.getEmail());
        }

    }


    @DisplayName("Test. Json файл не пуст")
    @Test
    void test1() {
        Assertions.assertNotNull(myPojo2.length, "Json file is empty");
    }


    @DisplayName("Test. Провалидировать Json")
    @Test
    void test2a() {
        JsonElement parse = null;
        try{
            JsonParser parser = new JsonParser();
            parse = parser.parse(jsonOrig);
            LOGGER.info(parse);
            Assertions.assertTrue(parse.toString().length() == 437);
        }
        catch(JsonSyntaxException ex){
            LOGGER.info("Not a valid Json String:"+ex.getMessage());
            Assertions.assertNull(parse);
        }
    }


    @DisplayName("Test. Провалидировать Json. Валидный json")
    @Test
    void test2b() {
        JsonElement parse = null;
        try{
            JsonParser parser = new JsonParser();
            parse = parser.parse(jsonOrig);
            LOGGER.info(parse);
        }
        catch(JsonSyntaxException ex){
            LOGGER.info("Not a valid Json String:"+ex.getMessage());
        }
    }


    @DisplayName("Test. Коллекция состоит из трех массивов данных")
    @Test
    void test3() {
        Assertions.assertEquals(3, myPojo2.length, "Incorrect array length");
    }


    @DisplayName("Test. Id начинается с 1 и заканчивается 3")
    @Test
    void test4() {
        int x = 1;
        for (MyPojo2 pojo2 : myPojo2){
            System.out.println(pojo2.getId());
            Assertions.assertEquals(x, pojo2.getId(), "Incorrect it number: " +pojo2.getId());
            x++;
        }
    }


    @DisplayName("Test. Все данные по каждому пользователю совпадают из файла с БД")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка данных")
    @CsvSource({"1, 0", "2, 1", "3, 2"})
    void test5Name(int id, int mypojoNum) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("ID", id);
        List<MyPojo2> queryPojo = nquOracle.query("SELECT * FROM PERSON WHERE ID = :ID",
                param,
                new BeanPropertyRowMapper<>(MyPojo2.class));

        Assertions.assertEquals(queryPojo.get(0).getFirst_name(), myPojo2[mypojoNum].getFirst_name(), "Incorrect First_name");

        assertAll( "Positibe testing",
                () -> Assertions.assertEquals(queryPojo.get(0).getLast_name(), myPojo2[mypojoNum].getLast_name(), "Incorrect Last_name"),
                () -> Assertions.assertEquals(queryPojo.get(0).getBirthday(), myPojo2[mypojoNum].getBirthday(), "Incorrect Birthday"),
                () -> Assertions.assertEquals(queryPojo.get(0).getEmail(), myPojo2[mypojoNum].getEmail(), "Incorrect Email"),
                () -> Assertions.assertEquals(queryPojo.get(0).getPhone(), myPojo2[mypojoNum].getPhone(), "Incorrect Phone"),
                () -> Assertions.assertEquals(queryPojo.get(0).getJob(), myPojo2[mypojoNum].getJob(), "Incorrect Job"));
    }


    @DisplayName("Test. Имя и Фамилия каждого пользователя с заглавной буквы")
    @Test
    void test6a() {
        for (MyPojo2 pojo2 : myPojo2){
            Assertions.assertTrue(pojo2.getFirst_name().substring(0,1).matches("[A-Z]"), "Incorrect first character" +pojo2.getFirst_name());
        }
    }


    @DisplayName("Test. Имя и Фамилия каждого пользователя с заглавной буквы. Второй способ")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка First_name")
    @ValueSource(ints = {0, 1, 2})
    void test6b(int x) {
        Assertions.assertTrue(myPojo2[x].getFirst_name().substring(0,1).matches("[A-Z]"), "Incorrect first character at array: " +x);
    }


    @DisplayName("Test. Имя и Фамилия каждого пользователя с заглавной буквы. Второй способ")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка Last_name")
    @ValueSource(ints = {0, 1, 2})
    void test6c(int x) {
        Assertions.assertTrue(myPojo2[x].getLast_name().substring(0,1).matches("[A-Z]"), "Incorrect first character at array: " +x);
    }


    @DisplayName("Test. Формат даты рождения каждого пользователя: YYYY-MM-DD")
    @Test
    void test7a() {
        for (MyPojo2 pojo2 : myPojo2){
            Assertions.assertTrue(pojo2.getBirthday().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"), "Incorrect Birthday format");
        }
    }


    @DisplayName("Test. Формат даты рождения каждого пользователя: YYYY-MM-DD. Второй способ")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка Birthday")
    @ValueSource(ints = {0, 1, 2})
    void test7b(int x) {
            Assertions.assertTrue(myPojo2[x].getBirthday().matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$"), "Incorrect Birthday format");
    }


    @DisplayName("Test. Провалидировать Email")
    @Test
    void test8a() {
        for (MyPojo2 pojo2 : myPojo2){
            Assertions.assertTrue(pojo2.getEmail().matches("(.+@.+\\..+)"), "Incorrect Email format");
        }
    }


    @DisplayName("Test. Провалидировать Email. Второй способ")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка Email")
    @ValueSource(ints = {0, 1, 2})
    void test8b(int x) {
            Assertions.assertTrue(myPojo2[x].getEmail().matches("(.+@.+\\..+)"), "Incorrect Email format");
    }


    @DisplayName("Test. У пользователя id = 1 и id = 2 email удовлетворяет маске: @yahoo")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка Email yahoo")
    @ValueSource(ints = {0, 1, 2})
    void test9(int x) {

        if (x !=2){
            Assertions.assertTrue(myPojo2[x].getEmail().matches("(.+@yahoo+\\..+)"), "Incorrect Email yahoo format");
        }else {
            Assertions.assertFalse(myPojo2[x].getEmail().matches("(.+@yahoo+\\..+)"), "Incorrect Email format");
        }
    }


    @DisplayName("Test. Формат записи телефона удовлетворяет маске: DDD-DDD-DDDD")
    @ParameterizedTest(name = "{index} => массив =''{0}'' - проверка phone")
    @ValueSource(ints = {0, 1, 2})
    void test10(int x) {
            Assertions.assertTrue(myPojo2[x].getPhone().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$"), "Incorrect phone format");
    }

}
