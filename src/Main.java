/*
Задание:
    13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.
    14. Написать программу, имитирующую работу реестра домашних животных.

    В программе должен быть реализован следующий функционал:
    14.1 Завести новое животное
    14.2 определять животное в правильный класс
    14.3 увидеть список команд, которое выполняет животное
    14.4 обучить животное новым командам
    14.5 Реализовать навигацию по меню

    15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆
    значение внутренней̆ int переменной̆ на 1 при нажатии “Завести новое
    животное” Сделайте так, чтобы с объектом такого типа можно было работать в
    блоке try-with-resources. Нужно бросить исключение, если работа с объектом
    типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
    считать в ресурсе try, если при заведении животного заполнены все поля.
 */

import model.Animal;
import model.animals.*;
import view.ConsoleView;
import view.CreatedAnimal;
import view.TrainedAnimal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ConsoleView view;
    private static List<Animal> animals;

    public static void main(String[] args) {
        view = new ConsoleView();
        animals = new ArrayList<>();

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

    private static void lookupAnimals() {
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

    private static void trainAnimal() {
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

    private static void createNewAnimal() {
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
    }
}