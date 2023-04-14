import model.Animal;
import model.animals.*;
import view.ConsoleView;
import view.CreatedAnimal;
import view.TrainedAnimal;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private final ConsoleView view;
    private final List<Animal> animals;

    public Program() {
        view = new ConsoleView();
        animals = new ArrayList<>();
    }

    public void run() {
        while (true) {
            switch (view.displayMainMenu()) {
                case NEW_ANIMAL -> createNewAnimal();
                case LOOKUP_ANIMALS -> lookupAnimals();
                case TRAIN_ANIMAL -> trainAnimal();
                case EXIT -> {
                    view.displayMessage("Bye bye!");
                    return;
                }
                default -> view.displayMessage("! Команда не поддерживается");
            }
        }
    }

    private void lookupAnimals() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список животных:\n");
        if (animals.size() == 0) {
            sb.append("Пусто\n");
        }

        for (Animal animal : animals) {
            sb.append(animal.toString());
            sb.append("\n");
        }

        view.displayMessage(sb.toString());
    }

    private void trainAnimal() {
        if (animals.size() == 0) {
            view.displayMessage("Нет животных для тренировки!");
            return;
        }

        TrainedAnimal trainedAnimal = view.trainAnimal(animals);
        for (Animal animal : animals) {
            if (animal.getId() == trainedAnimal.getAnimalID()) {
                animal.addCommand(trainedAnimal.getCommand());
                view.displayMessage("Животное успешно натренировано!");
                break;
            }
        }
    }

    private void createNewAnimal() {
        try (Counter counter = new Counter()) {
            CreatedAnimal createdAnimal = view.createNewAnimal();

            Animal animal;
            switch (createdAnimal.getType()) {
                case CAMEL -> animal = new Camel(createdAnimal.getName(), createdAnimal.getBirthday());
                case CAT -> animal = new Cat(createdAnimal.getName(), createdAnimal.getBirthday());
                case DOG -> animal = new Dog(createdAnimal.getName(), createdAnimal.getBirthday());
                case DONKEY -> animal = new Donkey(createdAnimal.getName(), createdAnimal.getBirthday());
                case HAMSTER -> animal = new Hamster(createdAnimal.getName(), createdAnimal.getBirthday());
                case HORSE -> animal = new Horse(createdAnimal.getName(), createdAnimal.getBirthday());
                default -> {
                    view.displayMessage("! Уп-с: что-то пошло не так...");
                    return;
                }
            }

            view.displayMessage("Животное успешно добавлено!");
            animals.add(animal);
            counter.add();
            view.displayMessage("Добавленных животных: " + counter.getCounter());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
