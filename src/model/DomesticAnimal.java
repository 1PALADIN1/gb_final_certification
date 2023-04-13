package model;

import java.util.Date;

public class DomesticAnimal extends Animal {
    public DomesticAnimal(String name, Date birthday) {
        super(AnimalType.DOMESTIC, name, birthday);
    }
}
