package ru.gotoqa.Object2Json.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muflikhunov Roman
 */

@Getter
@Setter
public class MyPojoJ2O {

    public int id;
    public String name;
    public float price;
    public String tags[];
}
