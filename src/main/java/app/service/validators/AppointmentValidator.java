package app.service.validators;

import app.domain.Appointment;
import app.domain.Pets;

import java.sql.Time;
import java.util.Date;

public class AppointmentValidator {

    public void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la cita debe ser un número positivo.");
        }
    }

    public void validateDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("La fecha de la cita es obligatoria.");
        }
    }

    public void validateTime(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("La hora de la cita es obligatoria.");
        }
    }

    public void validateState(Boolean state) {
        if (state == null) {
            throw new IllegalArgumentException("El estado de la cita es obligatorio.");
        }
    }

    public void validateDirection(String direction) {
        if (direction == null || direction.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
    }

    public void validatePet(Pets pet) {
        if (pet == null) {
            throw new IllegalArgumentException("La cita debe estar asociada a una mascota.");
        }
    }

    public void validate(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
        validateId(appointment.getIdAppointment());
        validateDate(appointment.getDate());
        validateTime(appointment.getTime());
        validateState(appointment.getState());
        validateDirection(appointment.getDirection());
        validatePet(appointment.getPet());
    }
}