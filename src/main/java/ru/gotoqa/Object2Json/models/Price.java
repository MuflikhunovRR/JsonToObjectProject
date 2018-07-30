package ru.gotoqa.Object2Json.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Muflikhunov Roman
 */

@Getter
@Setter
public class Price {

    public String type;
    public int minimum;
    public boolean exclusiveMinimum;
}
