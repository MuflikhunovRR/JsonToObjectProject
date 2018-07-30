package ru.gotoqa.Object2Json.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muflikhunov Roman
 */

@Getter
@Setter
public class Tags {

    public String type;
    public Items items;
    public int minItems;
    public boolean uniqueItems;
}
