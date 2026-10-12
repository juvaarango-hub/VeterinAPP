package app.view;

import app.domain.Doctor;
import app.service.helpers.ConfirmationHelper;
import app.service.validators.DataTypeValidator;
import app.service.validators.DoctorValidator;

import java.util.List;

public class DoctorView {


    private final DataTypeValidator dataTypeValidator;
    private final DoctorValidator doctorValidator;
    private final ConfirmationHelper confirmationHelper;

    public DoctorView(DoctorValidator doctorValidator, DataTypeValidator dataTypeValidator, ConfirmationHelper confirmationHelper) {
        this.doctorValidator = doctorValidator;
        this.dataTypeValidator = dataTypeValidator;
        this.confirmationHelper = confirmationHelper;
    }

    public int requestDoctorId() {
        while (true) {
            int id = dataTypeValidator.validateInt("Identificación:");
            try {
                doctorValidator.validateId(id);
                return id;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public Doctor requestDoctorData(int id) {
        String name = requestDoctorField("Nombre:", doctorValidator::validateName);
        String lastName = requestDoctorField("Apellido:", doctorValidator::validateLastName);
        String email = requestDoctorField("Correo electrónico:", doctorValidator::validateEmail);
        String phone = requestDoctorField("Teléfono:", doctorValidator::validatePhone);
        String password = requestDoctorField("Contraseña:", doctorValidator::validatePassword);

        int idMp = dataTypeValidator.validateInt("Tarjeta Profesional (idMp):");
        doctorValidator.validateIdMp(idMp);

        String specialty = requestDoctorField("Especialidad:", doctorValidator::validateSpecialty);

        return new Doctor(id, name, lastName, email, phone, password, true, idMp, specialty);
    }

    private String requestDoctorField(String prompt, FieldValidator validator) {
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

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showDoctor(Doctor doctor) {
        System.out.println("\n--- Datos del Doctor ---");
        System.out.println("Identificación: " + doctor.getId());
        System.out.println("Nombre: " + doctor.getName());
        System.out.println("Apellido: " + doctor.getLastName());
        System.out.println("Correo: " + doctor.getEmail());
        System.out.println("Teléfono: " + doctor.getPhone());
        System.out.println("Tarjeta Profesional (MP): " + doctor.getIdMp());
        System.out.println("Especialidad: " + doctor.getSpecialty());
        System.out.println("Estado: " + (doctor.isState() ? "Activo" : "Inactivo"));
    }

    public void showDoctorNotFound(int id) {
        System.out.println("No se encontró ningún doctor con la identificación: " + id);
    }

    public void showDoctors(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE DOCTORES ---");
        for (Doctor doctor : doctors) {
            System.out.println("-------------------------");
            System.out.println("Identificación: " + doctor.getId());
            System.out.println("Nombre: " + doctor.getName() + " " + doctor.getLastName());
            System.out.println("Especialidad: " + doctor.getSpecialty());
            System.out.println("Tarjeta Profesional (MP): " + doctor.getIdMp());
            System.out.println("Estado: " + (doctor.isState() ? "Activo" : "Inactivo"));
        }
        System.out.println("-------------------------");
    }

    public Doctor requestDoctorUpdate(Doctor doctor) {
        System.out.println("\n--- Actualización de Doctor ---");
        System.out.println("Identificación: " + doctor.getId());

        String name = requestDoctorField("Nuevo nombre:", doctorValidator::validateName);
        String lastName = requestDoctorField("Nuevo apellido:", doctorValidator::validateLastName);
        String email = requestDoctorField("Nuevo correo electrónico:", doctorValidator::validateEmail);
        String phone = requestDoctorField("Nuevo teléfono:", doctorValidator::validatePhone);
        String password = requestDoctorField("Nueva contraseña:", doctorValidator::validatePassword);

        int idMp = dataTypeValidator.validateInt("Nueva Tarjeta Profesional (idMp):");
        doctorValidator.validateIdMp(idMp);

        String specialty = requestDoctorField("Nueva especialidad:", doctorValidator::validateSpecialty);

        return new Doctor(doctor.getId(), name, lastName, email, phone, password, doctor.isState(), idMp, specialty);
    }

    public boolean requestDeleteConfirmation() {
        return confirmationHelper.confirm("¿Está seguro de que desea eliminar este doctor?");
    }

}
