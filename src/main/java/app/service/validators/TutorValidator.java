package app.service.validators;

import app.domain.Tutor;

public class TutorValidator {

    // Validar el objeto completo
    public void validate(Tutor tutor) {

        if (tutor == null) {
            throw new IllegalArgumentException("El tutor no puede ser null.");
        }

        validateId(tutor.getId());
        validateName(tutor.getName());
        validateLastName(tutor.getLastName());
        validateEmail(tutor.getEmail());
        validatePhone(tutor.getPhone());
        validatePassword(tutor.getPassword());
        validateDirection(tutor.getDirection());
    }

    // Validar identificación
    public void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("La identificación debe ser un número positivo.");
        }
    }

    // Validar nombre
    public void validateName(String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("El nombre del tutor es obligatorio.");
        }
    }

    // Validar apellido
    public void validateLastName(String lastName) {
        if (isBlank(lastName)) {
            throw new IllegalArgumentException("El apellido del tutor es obligatorio.");
        }
    }

    // Validar correo electrónico
    public void validateEmail(String email) {

        if (isBlank(email)) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
    }

    // Validar teléfono
    public void validatePhone(String phone) {

        if (isBlank(phone)) {
            throw new IllegalArgumentException("El teléfono del tutor es obligatorio.");
        }

        if (!phone.matches("^\\+?[0-9() -]+$") ||
                !phone.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
    }

    // Validar contraseña
    public void validatePassword(String password) {
        if (isBlank(password)) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
    }

    // Validar dirección
    public void validateDirection(String direction) {
        if (isBlank(direction)) {
            throw new IllegalArgumentException("La dirección del tutor es obligatoria.");
        }
    }

    // Método auxiliar para comprobar valores vacíos
    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}