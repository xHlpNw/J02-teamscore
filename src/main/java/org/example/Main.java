package org.example;

import org.example.Task5.AlreadyExistsException;
import org.example.Task5.InputHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHistory history = new InputHistory();

        System.out.println("Введите значение ('exit' для завершения работы программы):");

        while (true) {
            System.out.print((history.getSize() + 1) + ": ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Завершение работы");
                break;
            }
            try {
                history.add(input);
            } catch (AlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}