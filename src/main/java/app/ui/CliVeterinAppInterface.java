package app.ui;

import java.util.Scanner;

public class CliVeterinAppInterface {

    private final TutorUI tutorUI;
    private final PetsUI petsUI;
    private final DoctorUI doctorUI;
    private final Scanner scanner;

    public CliVeterinAppInterface(
            TutorUI tutorUI,
            PetsUI petsUI,
            DoctorUI doctorUI,
            Scanner scanner
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
            System.out.print("Seleccione una opción: ");

            option = readOption();

            switch (option) {
                case 1:
                    tutorMenu();
                    break;

                case 2:
                    petsMenu();
                    break;

                case 3:
                    doctorUI.handleDoctorMenu(); // O un método doctorMenu() que delegue a esto
                    break;

                case 0:
                    System.out.println("Saliendo de VeterinAPP...");
                    break;

                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    private void tutorMenu() {
        int option;

        do {
            System.out.println("\n--- GESTIÓN DE TUTORES ---");
            System.out.println("1. Registrar tutor");
            System.out.println("2. Buscar tutor por identificación");
            System.out.println("3. Listar tutores");
            System.out.println("4. Actualizar tutor");
            System.out.println("5. Eliminar tutor");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            option = readOption();

            switch (option) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                case 1:
                    tutorUI.registerTutor();
                    break;

                case 2:
                    tutorUI.findTutorById();
                    break;

                case 3:
                    tutorUI.listTutors();
                    break;

                case 4:
                    tutorUI.updateTutor();
                    break;

                case 5:
                    tutorUI.deleteTutor();
                    break;

                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    private void petsMenu() {
        int option;

        do {
            System.out.println("\n--- GESTIÓN DE MASCOTAS ---");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Buscar mascota por ID");
            System.out.println("3. Listar mascotas");
            System.out.println("4. Actualizar mascota");
            System.out.println("5. Eliminar mascota");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            option = readOption();

            switch (option) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                case 1:
                    petsUI.registerPet();
                    break;

                case 2:
                    petsUI.findPetById();
                    break;

                case 3:
                    petsUI.listPets();
                    break;

                case 4:
                    petsUI.updatePet();
                    break;

                case 5:
                    petsUI.deletePet();
                    break;

                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    private int readOption() {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
