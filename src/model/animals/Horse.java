package model.animals;

import model.Command;
import model.PackAnimal;

import java.util.Date;

public final class Horse extends PackAnimal {
    public Horse(String name, Date birthday) {
        super(name, birthday);

        // default every dog command
        addCommand(Command.GALLOP);
    }
}
