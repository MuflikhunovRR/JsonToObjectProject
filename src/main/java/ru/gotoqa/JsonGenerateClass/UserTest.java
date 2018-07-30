package ru.gotoqa.JsonGenerateClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ru.gotoqa.Json2Object.App2;

import java.io.File;
import java.net.URL;
import java.io.IOException;

/**
 * @author Muflikhunov Roman
 */

public class UserTest {
    public static final Logger LOGGER = LogManager.getLogger(UserTest.class);

    public static void main(String[] args) throws IOException {
        File jsonFile = new File("D:\\JAVA\\Java_SRC\\Json2Object\\src\\main\\resources\\user.json");
        URL jsonUrl = new URL("https://gist.githubusercontent.com/MuflikhunovRR/bc84c50022389b4ca0a52b3606594e44/raw/ae5e8fe7898adc0eff53abb4a591222c34dc11d1/user.json");
        String jsonStr =
                "{\"name\":{\"first\":\"Joe\",\"last\":\"Sixpack\"},\"gender\":\"MALE\",\"verified\":false,\"userImage\":\"SSBsb3ZlIE15IEZhbWlseSAh\"}";

        User user = null;
        ObjectMapper mapper = new ObjectMapper();

        user = mapper.readValue(jsonFile, User.class);
        LOGGER.info(user.getName().getFirst());

        user = mapper.readValue(jsonUrl, User.class);
        LOGGER.info(user.getName().getLast());

        user = mapper.readValue(jsonStr, User.class);
        LOGGER.info(user.getGender());

        LOGGER.info(new String(user.getUserImage()));
    }
}
