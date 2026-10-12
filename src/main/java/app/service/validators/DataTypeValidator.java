package app.service.validators;

import java.util.Scanner;

public class DataTypeValidator {

    private final Scanner scanner;

    public DataTypeValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    // Solicita texto obligatorio
    public String validateString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Error: este campo es obligatorio.");
        }
    }

    // Solicita un número entero
    public int validateInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número entero válido.");
            }
        }
    }

    // Solicita un número decimal
    public double validateDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número decimal válido.");
            }
        }
    }

    // Solicita un número decimal de tipo float
    public float validateFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número decimal válido.");
            }
        }
    }
}