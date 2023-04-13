package model.animals;

import model.PackAnimal;

import java.util.Date;

public final class Donkey extends PackAnimal {
    public Donkey(String name, Date birthday) {
        super(name, birthday);
    }

    @Override
    protected String getClassName() {
        return "Осел";
    }
}
