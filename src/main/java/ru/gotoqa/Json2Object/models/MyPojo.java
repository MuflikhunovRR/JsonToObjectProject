package ru.gotoqa.Json2Object.models;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Muflikhunov Roman
 */

@Setter
@Getter
public class MyPojo {

    public int id;
    public String name;
    public float price;
    public String tags[];
}
