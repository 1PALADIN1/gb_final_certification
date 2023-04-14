package view;

import model.Animal;
import model.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class ConsoleView {
    public ConsoleView() {
        System.out.println("Добро пожаловать в реест домашних животных!");
    }

    public MainMenuOutput displayMainMenu() {
        while (true) {
            System.out.println("Выберете действие:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Посмотреть животных");
            System.out.println("3. Обучить животное команде");
            System.out.println("4. Завершить программу");

            try {
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                return switch (input) {
                    case 1 -> MainMenuOutput.NEW_ANIMAL;
                    case 2 -> MainMenuOutput.LOOKUP_ANIMALS;
                    case 3 -> MainMenuOutput.TRAIN_ANIMAL;
                    case 4 -> MainMenuOutput.EXIT;
                    default -> throw new RuntimeException("! Некорректный пункт меню. Попробуйте снова.");
                };

            } catch (InputMismatchException e) {
                System.out.println("! Некорректный пункт меню. Попробуйте снова.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public CreatedAnimal createNewAnimal() {
        System.out.println("Введите название животного:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();

        Date birthdate;
        while (true) {
            System.out.println("Введите дату рождения (формат: 20.02.2021):");
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

            try {
                scanner = new Scanner(System.in);
                birthdate = formatter.parse(scanner.next());
                break;
            } catch (ParseException e) {
                System.out.println("! Не удалось распарсить дату рождения. Попробуйте снова.");
            }
        }

        AnimalType type;
        while (true) {
            System.out.println("Выберете класс:");
            System.out.println("1. Верблюд");
            System.out.println("2. Кот");
            System.out.println("3. Собака");
            System.out.println("4. Осел");
            System.out.println("5. Хомяк");
            System.out.println("6. Лошадь");

            try {
                scanner = new Scanner(System.in);
                switch (scanner.nextInt()) {
                    case 1 -> type = AnimalType.CAMEL;
                    case 2 -> type = AnimalType.CAT;
                    case 3 -> type = AnimalType.DOG;
                    case 4 -> type = AnimalType.DONKEY;
                    case 5 -> type = AnimalType.HAMSTER;
                    case 6 -> type = AnimalType.HORSE;
                    default -> {
                        throw new RuntimeException();
                    }
                }

                break;
            } catch (Exception e) {
                System.out.println("! Некорректный пункт меню. Попробуйте снова.");
            }
        }

        return new CreatedAnimal(name, birthdate, type);
    }

    public TrainedAnimal trainAnimal(List<Animal> animals) {
        int id;
        while (true) {
            System.out.println("Выберете животное (id), которое хотите натренировать:");
            for (Animal animal : animals) {
                System.out.println(animal.toString());
            }

            Scanner scanner = new Scanner(System.in);
            try {
                id = scanner.nextInt();

                boolean found = false;
                for (Animal animal : animals) {
                    if (animal.getId() == id) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    throw new RuntimeException();
                }

                break;
            } catch (Exception e) {
                System.out.println("! Некорректный id животного. Попробуйте снова.");
            }
        }

        Command command;
        while (true) {
            System.out.println("Выберете команду:");
            System.out.println("1. Сидеть");
            System.out.println("2. Лежать");
            System.out.println("3. Стоять");
            System.out.println("4. Голос");
            System.out.println("5. Рысь");
            System.out.println("6. Галоп");

            Scanner scanner = new Scanner(System.in);
            try {
                int number = scanner.nextInt();
                switch (number) {
                    case 1 -> command = Command.SIT;
                    case 2 -> command = Command.LIE;
                    case 3 -> command = Command.STAND;
                    case 4 -> command = Command.VOICE;
                    case 5 -> command = Command.TROT;
                    case 6 -> command = Command.GALLOP;
                    default -> throw new RuntimeException();
                }

                break;
            } catch (Exception e) {
                System.out.println("! Некорректный id животного. Попробуйте снова.");
            }
        }

        return new TrainedAnimal(id, command);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
