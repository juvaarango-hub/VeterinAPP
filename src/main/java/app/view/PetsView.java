package app.view;

import app.domain.Pets;
import app.domain.Tutor;
import app.domain.enums.PetSexEnum;
import app.service.helpers.SelectPetSex;
import app.service.validators.DataTypeValidator;
import app.service.helpers.ConfirmationHelper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PetsView {

    private final DataTypeValidator dataTypeValidator;
    private final SelectPetSex selectPetSex;
    private final ConfirmationHelper confirmationHelper;

    public PetsView(
            DataTypeValidator dataTypeValidator,
            SelectPetSex selectPetSex,
            ConfirmationHelper confirmationHelper) {

        this.dataTypeValidator = dataTypeValidator;
        this.selectPetSex = selectPetSex;
        this.confirmationHelper = confirmationHelper;
    }

    // Solicitar datos para registrar una mascota.
    // El ID se genera automáticamente en PetsServiceImpl.
    public Pets requestPetData(Tutor tutor) {

        String name = dataTypeValidator.validateString("Nombre de la mascota: ");
        String species = dataTypeValidator.validateString("Especie: ");
        String breed = dataTypeValidator.validateString("Raza: ");

        // Seleccionar el sexo mediante el helper.
        PetSexEnum selectedSex = selectPetSex.getPetSex();
        boolean sex = selectedSex.getValue();

        double weight = requestWeight();

        LocalDate birthDate = requestBirthDate();

        return new Pets(
                0,
                name,
                species,
                breed,
                sex,
                weight,
                birthDate,
                tutor
        );
    }

    // Solicitar los nuevos datos de una mascota.
    public Pets requestPetUpdateData(Pets existingPet, Tutor tutor) {

        showMessage("\nIngrese los nuevos datos de la mascota:");

        String name = dataTypeValidator.validateString("Nombre de la mascota: ");
        String species = dataTypeValidator.validateString("Especie: ");
        String breed = dataTypeValidator.validateString("Raza: ");

        PetSexEnum selectedSex = selectPetSex.getPetSex();
        boolean sex = selectedSex.getValue();

        double weight = requestWeight();
        LocalDate birthDate = requestBirthDate();

        return new Pets(
                existingPet.getId(),
                name,
                species,
                breed,
                sex,
                weight,
                birthDate,
                tutor
        );
    }

    // Solicitar el peso.
    private double requestWeight() {

        while (true) {
            double weight = dataTypeValidator.validateDouble("Peso de la mascota en kg: ");

            if (Double.isFinite(weight) && weight > 0) {
                return weight;
            }

            System.out.println("Error: el peso debe ser un número positivo.");
        }
    }

    // Solicitar la fecha de nacimiento.
    private LocalDate requestBirthDate() {

        while (true) {
            String value = dataTypeValidator.validateString("Fecha de nacimiento (AAAA-MM-DD): ");

            try {
                LocalDate birthDate = LocalDate.parse(value);

                if (birthDate.isAfter(LocalDate.now())) {
                    System.out.println("Error: la fecha no puede estar en el futuro.");
                    continue;
                }

                return birthDate;

            } catch (DateTimeParseException e) {
                System.out.println("Error: use el formato AAAA-MM-DD, " + "por ejemplo 2023-05-18.");
            }
        }
    }

    // Mostrar un mensaje.
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Mostrar una mascota.
    public void showPet(Pets pet) {

        if (pet == null) {
            System.out.println("No se encontró la mascota.");
            return;
        }

        System.out.println("\n--- Datos de la mascota ---");
        System.out.println("ID: " + pet.getId());
        System.out.println("Nombre: " + pet.getName());
        System.out.println("Especie: " + pet.getSpecies());
        System.out.println("Raza: " + pet.getBreed());
        System.out.println("Sexo: " + (pet.isSex() ? "Macho" : "Hembra"));
        System.out.println("Peso: " + pet.getWeight() + " kg");
        System.out.println("Fecha de nacimiento: " + pet.getBirthDate());

        if (pet.getTutor() != null) {
            System.out.println("Tutor: " + pet.getTutor().getName() + " " + pet.getTutor().getLastName());

            System.out.println("Identificación del tutor: " + pet.getTutor().getId());
        }
    }

    // Mostrar mascota por ID
    public int requestPetId() {
        while (true) {
            int id = dataTypeValidator.validateInt("ID de la mascota: ");

            if (id > 0) {
                return id;
            }

            System.out.println("Error: el ID debe ser un número positivo.");
        }
    }

    // Solicitar confirmación antes de eliminar una mascota.
    public boolean confirmPetDeletion() {
        return confirmationHelper.confirm("¿Está seguro de que desea eliminar esta mascota?");
    }

    // Mostrar una mascota que no se encontró.
    public void showPetNotFound(int id) {
        System.out.println("No se encontró ninguna mascota con el ID: " + id);
    }

    // Mostrar todas las mascotas.
    public void showPets(List<Pets> pets) {

        if (pets.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }

        System.out.println("\n--- LISTA DE MASCOTAS ---");

        for (Pets pet : pets) {
            showPet(pet);
            System.out.println("-------------------------");
        }
    }
}
