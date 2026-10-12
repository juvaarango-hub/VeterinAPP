package app.service.validators;

import app.domain.Doctor;

public class DoctorValidator {

    public void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("La identificación debe ser un número positivo.");
        }
    }

    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    public void validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
    }

    public void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
    }

    public void validatePhone(String phone) {
        if (phone == null || phone.trim().length() < 7) {
            throw new IllegalArgumentException("El teléfono debe tener al menos 7 dígitos.");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.trim().length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }
    }

    public void validateIdMp(int idMp) {
        if (idMp <= 0) {
            throw new IllegalArgumentException("El ID de tarjeta profesional (idMp) debe ser positivo.");
        }
    }

    public void validateSpecialty(String specialty) {
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
    }

    public void validate(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("El doctor no puede ser nulo.");
        }
        validateId(doctor.getId());
        validateName(doctor.getName());
        validateLastName(doctor.getLastName());
        validateEmail(doctor.getEmail());
        validatePhone(doctor.getPhone());
        validatePassword(doctor.getPassword());
        validateIdMp(doctor.getIdMp());
        validateSpecialty(doctor.getSpecialty());
    }
}
