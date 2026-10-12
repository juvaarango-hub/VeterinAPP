package app.view;

import app.domain.Tutor;
import app.service.validators.DataTypeValidator;
import app.service.validators.TutorValidator;
import app.service.helpers.ConfirmationHelper;

import java.util.List;

public class TutorView {

    private final DataTypeValidator dataTypeValidator;
    private final TutorValidator tutorValidator;
    private final ConfirmationHelper confirmationHelper;

    public TutorView(
            TutorValidator tutorValidator,
            DataTypeValidator dataTypeValidator,
            ConfirmationHelper confirmationHelper) {

        this.tutorValidator = tutorValidator;
        this.dataTypeValidator = dataTypeValidator;
        this.confirmationHelper = confirmationHelper;
    }

    // Solicitar identificación
    public int requestTutorId() {
        while (true) {
            int id = dataTypeValidator.validateInt("Identificación:");

            try {
                tutorValidator.validateId(id);
                return id;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Solicitar datos para registrar un tutor
    public Tutor requestTutorData(int id) {

        String name = requestTutorField("Nombre:", tutorValidator::validateName);
        String lastName = requestTutorField("Apellido:", tutorValidator::validateLastName);
        String email = requestTutorField("Correo electrónico:", tutorValidator::validateEmail);
        String phone = requestTutorField("Teléfono:", tutorValidator::validatePhone);
        String password = requestTutorField("Contraseña:", tutorValidator::validatePassword);
        String direction = requestTutorField("Dirección:", tutorValidator::validateDirection);

        return new Tutor(
                id,
                name,
                lastName,
                email,
                phone,
                password,
                true,
                direction
        );
    }

    // Solicitar texto y aplicar la validación específica del tutor
    private String requestTutorField(
            String prompt,
            FieldValidator validator
    ) {
        while (true) {
            String value = dataTypeValidator.validateString(prompt);

            try {
                validator.validate(value);
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface FieldValidator {
        void validate(String value);
    }

    // Mostrar un mensaje
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Mostrar un tutor
    public void showTutor(Tutor tutor) {
        System.out.println("\n--- Datos del tutor ---");
        System.out.println("Identificación: " + tutor.getId());
        System.out.println("Nombre: " + tutor.getName());
        System.out.println("Apellido: " + tutor.getLastName());
        System.out.println("Correo: " + tutor.getEmail());
        System.out.println("Teléfono: " + tutor.getPhone());
        System.out.println("Dirección: " + tutor.getDirection());
    }

    // Mostrar tutor no encontrado
    public void showTutorNotFound(int id) {
        System.out.println(
                "No se encontró ningún tutor con la identificación: " + id
        );
    }

    // Mostrar todos los tutores
    public void showTutors(List<Tutor> tutors) {

        if (tutors.isEmpty()) {
            System.out.println("No hay tutores registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE TUTORES ---");

        for (Tutor tutor : tutors) {
            System.out.println("-------------------------");
            System.out.println("Identificación: " + tutor.getId());
            System.out.println("Nombre: " + tutor.getName());
            System.out.println("Apellido: " + tutor.getLastName());
            System.out.println("Correo: " + tutor.getEmail());
            System.out.println("Teléfono: " + tutor.getPhone());
            System.out.println("Dirección: " + tutor.getDirection());
            System.out.println(
                    "Estado: " + (tutor.isState() ? "Activo" : "Inactivo")
            );
        }

        System.out.println("-------------------------");
    }

    // Solicitar datos para actualizar un tutor
    public Tutor requestTutorUpdate(Tutor tutor) {

        System.out.println("\n--- Actualización de tutor ---");
        System.out.println("Identificación: " + tutor.getId());

        String name = requestTutorField("Nuevo nombre:", tutorValidator::validateName);
        String lastName = requestTutorField("Nuevo apellido:", tutorValidator::validateLastName);
        String email = requestTutorField("Nuevo correo electrónico:", tutorValidator::validateEmail);
        String phone = requestTutorField("Nuevo teléfono:", tutorValidator::validatePhone);
        String password = requestTutorField("Nueva contraseña:", tutorValidator::validatePassword);
        String direction = requestTutorField("Nueva dirección:", tutorValidator::validateDirection);

        return new Tutor(
                tutor.getId(),
                name,
                lastName,
                email,
                phone,
                password,
                tutor.isState(),
                direction
        );
    }

    // Confirmar eliminación
    public boolean requestDeleteConfirmation() {
        return confirmationHelper.confirm("¿Está seguro de que desea eliminar este tutor?");
    }
}