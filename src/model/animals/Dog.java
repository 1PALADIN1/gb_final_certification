package model.animals;

import model.Command;
import model.DomesticAnimal;

import java.util.Date;

public final class Dog extends DomesticAnimal {
    public Dog(String name, Date birthday) {
        super(name, birthday);

        // default every dog command
        addCommand(Command.SIT);
        addCommand(Command.STAND);
    }

    @Override
    protected String getClassName() {
        return "Собака";
    }
}
