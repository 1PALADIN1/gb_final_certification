package view;

import java.util.Date;

public final class CreatedAnimal {
    private final String name;
    private final Date birthday;
    private final AnimalType type;

    public CreatedAnimal(String name, Date birthday, AnimalType type) {
        this.name = name;
        this.birthday = birthday;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public AnimalType getType() {
        return type;
    }
}
