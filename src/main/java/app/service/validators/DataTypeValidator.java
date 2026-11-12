package app.service.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Solicita fecha y hora
    public LocalDateTime validateDateTime(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                return LocalDateTime.parse(value, DATE_TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Error: use el formato dd/MM/yyyy HH:mm, por ejemplo 15/11/2026 09:30.");
            }
        }
    }

}