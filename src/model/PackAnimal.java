package model;

import java.util.Date;

public abstract class PackAnimal extends Animal {
    public PackAnimal(String name, Date birthday) {
        super(AnimalType.PACK, name, birthday);
    }
}
