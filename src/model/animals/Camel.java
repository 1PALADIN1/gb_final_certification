package model.animals;

import model.PackAnimal;

import java.util.Date;

public final class Camel extends PackAnimal {
    public Camel(String name, Date birthday) {
        super(name, birthday);
    }

    @Override
    protected String getClassName() {
        return "Верблюд";
    }
}
