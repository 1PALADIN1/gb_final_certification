package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Animal {
    private final AnimalType type;
    private final String name;
    private final Date birthday;
    private final List<Command> commands;

    public Animal(AnimalType type, String name, Date birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
        this.commands = new ArrayList<>();
    }

    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public String toString() {
        return "Animal: " + name + " [" + type + "]";
    }
}
