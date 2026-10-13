package app.ui;

import java.util.Scanner;

public class CliVeterinAppInterface {

    private final TutorUI tutorUI;
    private final PetsUI petsUI;
    private final DoctorUI doctorUI;
    private final Scanner scanner;

    public CliVeterinAppInterface(TutorUI tutorUI, PetsUI petsUI, DoctorUI doctorUI, Scanner scanner
    ) {
        this.tutorUI = tutorUI;
        this.petsUI = petsUI;
        this.doctorUI = doctorUI;
        this.scanner = scanner;
    }

    public void applicationInit() {
        int option;

        System.out.println("================================");
        System.out.println("       BIENVENIDO A VETERINAPP");
        System.out.println("================================");

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar tutores");
            System.out.println("2. Gestionar mascotas");
            System.out.println("3. Gestionar doctores");
            System.out.println("0. Salir");

            option = readOption();

            switch (option) {
                case 1:
                    tutorUI.handleTutorMenu();
                    break;

                case 2:
                    petsUI.handlePetsMenu();
                    break;

                case 3:
                    doctorUI.handleDoctorMenu();
                    break;

                case 0:
                    System.out.println("Saliendo de VeterinAPP...");
                    break;

                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    private int readOption() {
        String input;

        System.out.print("Seleccione una opción: ");
        input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(
                    "Error: ingrese un número entero válido."
            );
            return -1;
        }
    }
}

