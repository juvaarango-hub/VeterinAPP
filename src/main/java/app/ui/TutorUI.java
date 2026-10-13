package app.ui;

import app.domain.Tutor;
import app.service.inputports.TutorService;
import app.view.TutorView;
import app.service.validators.DataTypeValidator;

public class TutorUI {

    private final TutorService tutorService;
    private final TutorView tutorView;
    private final DataTypeValidator dataTypeValidator;

    public TutorUI(TutorService tutorService, TutorView tutorView, DataTypeValidator dataTypeValidator
    ) {
        this.tutorService = tutorService;
        this.tutorView = tutorView;
        this.dataTypeValidator = dataTypeValidator;
    }


    public void handleTutorMenu() {
        int option;

        do {
            System.out.println("\n--- GESTIÓN DE TUTORES ---");
            System.out.println("1. Registrar tutor");
            System.out.println("2. Buscar tutor por identificación");
            System.out.println("3. Listar tutores");
            System.out.println("4. Actualizar tutor");
            System.out.println("5. Eliminar tutor");
            System.out.println("0. Volver al menú principal");

            option = dataTypeValidator.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1:
                    registerTutor();
                    break;
                case 2:
                    findTutorById();
                    break;
                case 3:
                    listTutors();
                    break;
                case 4:
                    updateTutor();
                    break;
                case 5:
                    deleteTutor();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    public void registerTutor() {
        try {
            int id;

            while (true) {
                id = tutorView.requestTutorId();

                if (!tutorService.existsById(id)) {
                    break;
                }

                tutorView.showMessage("Ya existe un tutor con esa identificación. Intente con otra.");}

            Tutor tutor = tutorView.requestTutorData(id);

            tutorService.registerTutor(tutor);

            tutorView.showMessage("Tutor registrado correctamente.");

        } catch (IllegalArgumentException e) {
            tutorView.showMessage("Error al registrar el tutor: " + e.getMessage());
        }
    }

    public void findTutorById() {
        int id = tutorView.requestTutorId();

        Tutor tutor = tutorService.getTutorById(id);

        if (tutor == null) {
            tutorView.showTutorNotFound(id);
            return;
        }

        tutorView.showTutor(tutor);
    }

    public void listTutors() {
        tutorView.showTutors(tutorService.getAllTutors());
    }

    public void updateTutor() {
        int id = tutorView.requestTutorId();

        Tutor existingTutor = tutorService.getTutorById(id);

        if (existingTutor == null) {
            tutorView.showTutorNotFound(id);
            return;
        }

        tutorView.showTutor(existingTutor);

        Tutor updatedTutor = tutorView.requestTutorUpdate(existingTutor);

        Tutor result = tutorService.updateTutor(updatedTutor);

        if (result != null) {
            tutorView.showMessage("Tutor actualizado correctamente.");
        } else {
            tutorView.showMessage("No fue posible actualizar el tutor.");
        }
    }

    public void deleteTutor() {
        int id = tutorView.requestTutorId();

        Tutor tutor = tutorService.getTutorById(id);

        if (tutor == null) {
            tutorView.showTutorNotFound(id);
            return;
        }

        tutorView.showTutor(tutor);

        boolean confirm = tutorView.requestDeleteConfirmation();

        if (!confirm) {
            tutorView.showMessage("Eliminación cancelada.");
            return;
        }

        boolean deleted = tutorService.deleteTutor(id);

        if (deleted) {
            tutorView.showMessage("Tutor eliminado correctamente.");
        } else {
            tutorView.showMessage("No fue posible eliminar el tutor.");
        }
    }

}
