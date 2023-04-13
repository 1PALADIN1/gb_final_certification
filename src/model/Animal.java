package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Animal {
    private static int COUNTER = 1;
    private final int id;
    private final AnimalType type;
    private final String name;
    private final Date birthday;
    private final List<Command> commands;

    public Animal(AnimalType type, String name, Date birthday) {
        this.id = COUNTER++;
        this.type = type;
        this.name = name;
        this.birthday = birthday;
        this.commands = new ArrayList<>();
    }

    public int getId() {
        return id;
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
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ");
        sb.append(name);
        sb.append(" (");
        sb.append(type);
        sb.append(")");

        if (commands.size() > 0) {
            sb.append(", команды: ");
        }

        for (Command command : commands) {
            sb.append("[");
            sb.append(command);
            sb.append("]");
        }

        return sb.toString();
    }
}
