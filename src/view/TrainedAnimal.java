package view;

import model.Command;

public final class TrainedAnimal {
    private final int animalID;
    private final Command command;

    public TrainedAnimal(int animalID, Command command) {
        this.animalID = animalID;
        this.command = command;
    }

    public int getAnimalID() {
        return animalID;
    }

    public Command getCommand() {
        return command;
    }
}
