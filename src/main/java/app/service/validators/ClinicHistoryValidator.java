package app.service.validators;

import app.domain.Appointment;
import app.domain.ClinicHistory;
import app.domain.Doctor;

import java.time.LocalDateTime;

public class ClinicHistoryValidator {

    public void validateAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("La historia clínica debe estar asociada a una cita.");
        }
        if (appointment.getIdAppointment() == null || appointment.getIdAppointment() <= 0) {
            throw new IllegalArgumentException("El id de la cita debe ser un número positivo.");

        }
    }

    public void validateDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("La historia clínica debe tener un doctor.");
        }
    }

    public void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("La fecha de la atención es obligatoria.");
        }
    }

    public void validateSubjective(String subjective) {
        if (subjective == null || subjective.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo subjetivo no puede estar vacío.");
        }
    }

    public void validateObjective(String objective) {
        if (objective == null || objective.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo objetivo no puede estar vacío.");
        }
    }

    public void validateInterpretation(String interpretation) {
        if (interpretation == null || interpretation.trim().isEmpty()) {
            throw new IllegalArgumentException("La interpretación no puede estar vacía.");
        }
    }

    public void validatePlan(String plan) {
        if (plan == null || plan.trim().isEmpty()) {
            throw new IllegalArgumentException("El plan no puede estar vacío.");
        }
    }

    public void validate(ClinicHistory clinicHistory) {
        if (clinicHistory == null) {
            throw new IllegalArgumentException("La historia clínica no puede ser nula.");
        }
        validateAppointment(clinicHistory.getAppointment());
        validateDoctor(clinicHistory.getDoctor());
        validateDate(clinicHistory.getDate());
        validateSubjective(clinicHistory.getSubjective());
        validateObjective(clinicHistory.getObjective());
        validateInterpretation(clinicHistory.getInterpretation());
        validatePlan(clinicHistory.getPlan());
    }
}