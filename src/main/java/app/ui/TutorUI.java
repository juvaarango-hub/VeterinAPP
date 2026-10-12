package app.ui;

import app.domain.Tutor;
import app.service.inputports.TutorService;
import app.view.TutorView;

public class TutorUI {

    private final TutorService tutorService;
    private final TutorView tutorView;

    public TutorUI(TutorService tutorService, TutorView tutorView) {
        this.tutorService = tutorService;
        this.tutorView = tutorView;
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
