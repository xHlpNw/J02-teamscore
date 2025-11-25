package org.example;

import org.example.Task5.AlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> values = new ArrayList<>();

        System.out.println("Введите значение ('exit' для завершения работы программы):");
        int counter = 1;

        while (true) {
            System.out.print(counter + ": ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы");
                break;
            }
            try {
                if (values.contains(input)) {
                    throw new AlreadyExistsException(
                            input,
                            values.indexOf(input) + 1);
                }
                values.add(input);
                counter++;
            } catch (AlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}