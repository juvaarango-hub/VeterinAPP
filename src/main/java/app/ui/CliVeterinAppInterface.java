package app.ui;

import java.util.Scanner;

public class CliVeterinAppInterface {

    private final TutorUI tutorUI;
    private final PetsUI petsUI;
    private final DoctorUI doctorUI;
    private final AppointmentUI appointmentUI;
    private final Scanner scanner;
    private final ClinicHistoryUI clinicHistoryUI;

    public CliVeterinAppInterface(TutorUI tutorUI, PetsUI petsUI, DoctorUI doctorUI, AppointmentUI appointmentUI, ClinicHistoryUI clinicHistoryUI, Scanner scanner) {
        this.tutorUI = tutorUI;
        this.petsUI = petsUI;
        this.doctorUI = doctorUI;
        this.appointmentUI = appointmentUI;
        this.scanner = scanner;
        this.clinicHistoryUI = clinicHistoryUI;
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
            System.out.println("4. Gestionar citas");
            System.out.println("5. Gestionar historias clínicas");
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

                case 4:
                    appointmentUI.handleAppointmentMenu();
                    break;
                case 5:
                    clinicHistoryUI.handleClinicHistoryMenu();
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
        System.out.print("Seleccione una opción: ");
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese un número entero válido.");
            return -1;
        }
    }
}