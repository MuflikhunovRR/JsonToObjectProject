package ru.gotoqa.Object2Json.models;

import lombok.Getter;
import lombok.Setter;
import ru.gotoqa.Object2Json.models.Properties;

/**
 * @author Muflikhunov Roman
 */

@Getter
@Setter
public class MyPojoAdvanced {

    public String $schema;
    public String title;
    public String description;
    public String type;
    public Properties properties;
    public String [] required;

    public String get$schema() {
        return $schema;
    }

    public void set$schema(String $schema) {
        this.$schema = $schema;
    }
}
