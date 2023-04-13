package view;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
