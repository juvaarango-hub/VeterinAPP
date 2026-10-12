package app.ui;

import app.domain.Pets;
import app.domain.Tutor;
import app.service.inputports.PetsService;
import app.service.inputports.TutorService;
import app.view.PetsView;
import app.view.TutorView;
import java.util.List;

public class PetsUI {

    private final PetsService petsService;
    private final TutorService tutorService;
    private final PetsView petsView;
    private final TutorView tutorView;

    public PetsUI(
            PetsService petsService,
            TutorService tutorService,
            PetsView petsView,
            TutorView tutorView
    ) {
        this.petsService = petsService;
        this.tutorService = tutorService;
        this.petsView = petsView;
        this.tutorView = tutorView;
    }

    public void registerPet() {

        petsView.showMessage("\n--- Registro de mascota ---");
        petsView.showMessage("Ingrese la identificación del tutor responsable de la mascota.");
        int tutorId = tutorView.requestTutorId();

        Tutor tutor = tutorService.getTutorById(tutorId);

        if (tutor == null) {
            tutorView.showTutorNotFound(tutorId);
            return;
        }

        try {
            Pets pet = petsView.requestPetData(tutor);

            Pets registeredPet = petsService.registerPet(pet);

            petsView.showMessage("Mascota registrada correctamente.");
            petsView.showPet(registeredPet);

        } catch (IllegalArgumentException e) {
            petsView.showMessage("Error al registrar la mascota: " + e.getMessage());
        }
    }

    public void findPetById() {

        petsView.showMessage("\n--- Consulta de mascota ---");
        petsView.showMessage("Ingrese el ID de la mascota que desea consultar.");

        int id = petsView.requestPetId();

        Pets pet = petsService.getPetById(id);

        if (pet == null) {
            petsView.showPetNotFound(id);
            return;
        }

        petsView.showPet(pet);
    }

    public void listPets() {
        petsView.showMessage("\n--- Listado de mascotas ---");

        List<Pets> pets = petsService.getAllPets();

        petsView.showPets(pets);
    }

    public void updatePet() {
        petsView.showMessage("\n--- Actualizar mascota ---");

        int id = petsView.requestPetId();

        Pets existingPet = petsService.getPetById(id);

        if (existingPet == null) {
            petsView.showPetNotFound(id);
            return;
        }

        petsView.showMessage("\nTutor actual: " + existingPet.getTutor().getName() + " " + existingPet.getTutor().getLastName());
        petsView.showMessage("Ingrese la identificación del tutor responsable de la mascota.");

        int tutorId = tutorView.requestTutorId();

        Tutor tutor = tutorService.getTutorById(tutorId);

        if (tutor == null) {
            tutorView.showTutorNotFound(tutorId);
            return;
        }

        try {
            Pets updatedData = petsView.requestPetUpdateData(existingPet, tutor);
            Pets updatedPet = petsService.updatePet(updatedData);

            if (updatedPet == null) {
                petsView.showPetNotFound(id);
                return;
            }

            petsView.showMessage("Mascota actualizada correctamente.");
            petsView.showPet(updatedPet);

        } catch (IllegalArgumentException e) {
            petsView.showMessage("Error al actualizar la mascota: " + e.getMessage());
        }
    }

    public void deletePet() {
        petsView.showMessage("\n--- Eliminar mascota ---");

        int id = petsView.requestPetId();

        Pets pet = petsService.getPetById(id);

        if (pet == null) {
            petsView.showPetNotFound(id);
            return;
        }

        petsView.showPet(pet);

        if (!petsView.confirmPetDeletion()) {
            petsView.showMessage("Eliminación cancelada.");
            return;
        }

        boolean deleted = petsService.deletePet(id);

        if (deleted) {
            petsView.showMessage("Mascota eliminada correctamente.");
        } else {
            petsView.showMessage("No fue posible eliminar la mascota.");
        }
    }

}