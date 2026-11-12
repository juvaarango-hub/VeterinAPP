package app.view;

import app.domain.Appointment;
import app.domain.ClinicHistory;
import app.domain.Doctor;
import app.domain.Pets;
import app.service.validators.DataTypeValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClinicHistoryView {

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final DataTypeValidator dataTypeValidator;

    public ClinicHistoryView(DataTypeValidator dataTypeValidator) {
        this.dataTypeValidator = dataTypeValidator;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int requestDoctorId() {
        return dataTypeValidator.validateInt("Ingrese el ID del doctor que atendió: ");
    }

    public ClinicHistory requestClinicHistoryData(Appointment appointment, Doctor doctor) {
        showMessage("\n--- Datos de la historia clínica ---");
        return readFields(appointment, doctor);
    }

    public ClinicHistory requestClinicHistoryUpdateData(ClinicHistory existing, Doctor doctor) {
        showMessage("\n--- Nuevos datos de la historia clínica ---");
        return readFields(existing.getAppointment(), doctor);
    }

    public void showClinicHistory(ClinicHistory history) {
        showMessage("\n--- Historia clínica ---");
        showMessage("Cita: " + history.getAppointment().getIdAppointment());
        showMessage("Fecha: " + history.getDate().format(DATE_TIME_FORMAT));
        showMessage("Mascota: " + history.getAppointment().getPet().getName());
        showMessage("Tutor: " + history.getAppointment().getPet().getTutor().getName()
                + " " + history.getAppointment().getPet().getTutor().getLastName());
        showMessage("Doctor: " + history.getDoctor().getName() + " " + history.getDoctor().getLastName());
        showMessage("Subjetivo: " + history.getSubjective());
        showMessage("Objetivo: " + history.getObjective());
        showMessage("Interpretación: " + history.getInterpretation());
        showMessage("Plan: " + history.getPlan());
        showMessage("Observaciones: " + history.getObservation());
    }

    public void showClinicHistories(List<ClinicHistory> histories) {
        if (histories.isEmpty()) {
            showMessage("\nNo hay historias clínicas registradas.");
            return;
        }
        histories.forEach(this::showClinicHistory);
    }

    public void showPetClinicHistories(Pets pet, List<ClinicHistory> histories) {
        showMessage("\n=== HISTORIAL CLÍNICO DE " + pet.getName().toUpperCase() + " ===");
        showMessage("Tutor: " + pet.getTutor().getName() + " " + pet.getTutor().getLastName());

        if (histories.isEmpty()) {
            showMessage("\nLa mascota todavía no tiene historias clínicas registradas.");
            return;
        }
        histories.forEach(this::showClinicHistory);
    }

    public void showClinicHistoryNotFound(int appointmentId) {
        showMessage("No se encontró una historia clínica para la cita con ID: " + appointmentId);
    }

    public boolean confirmClinicHistoryDeletion() {
        while (true) {
            String answer = dataTypeValidator
                    .validateString("¿Está seguro de que desea eliminar la historia clínica? (s/n): ")
                    .toLowerCase();
            if (answer.equals("s") || answer.equals("si") || answer.equals("sí")) {
                return true;
            }
            if (answer.equals("n") || answer.equals("no")) {
                return false;
            }
            showMessage("Responda con s o n.");
        }
    }

    private ClinicHistory readFields(Appointment appointment, Doctor doctor) {
        LocalDateTime date = dataTypeValidator.validateDateTime("Fecha y hora de la atención (dd/MM/yyyy HH:mm): ");
        String subjective = dataTypeValidator.validateString("Subjetivo (motivo de consulta, lo que cuenta el tutor): ");
        String objective = dataTypeValidator.validateString("Objetivo (hallazgos del examen físico): ");
        String interpretation = dataTypeValidator.validateString("Interpretación (diagnóstico o análisis): ");
        String plan = dataTypeValidator.validateString("Plan (tratamiento y seguimiento): ");
        String observation = dataTypeValidator.validateString("Observaciones: ");

        return new ClinicHistory(appointment, date, subjective, objective,
                interpretation, plan, observation, doctor);
    }
}