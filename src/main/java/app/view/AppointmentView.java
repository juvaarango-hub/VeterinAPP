package app.view;

import app.domain.Appointment;
import app.domain.Pets;
import app.service.validators.DataTypeValidator;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AppointmentView {

    private final DataTypeValidator dataTypeValidator;

    public AppointmentView(DataTypeValidator dataTypeValidator) {
        this.dataTypeValidator = dataTypeValidator;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int requestAppointmentId() {
        return dataTypeValidator.validateInt("Ingrese el ID de la cita: ");
    }

    public Appointment requestAppointmentData(Pets pet) {
        showMessage("\n--- Datos de la cita ---");
        int id = requestAppointmentId();
        LocalDateTime dateTime = dataTypeValidator.validateDateTime("Fecha y hora de la cita (dd/MM/yyyy HH:mm): ");
        String direction = dataTypeValidator.validateString("Dirección: ");
        String observations = dataTypeValidator.validateString("Observaciones: ");

        return buildAppointment(id, dateTime, true, direction, observations, pet);
    }

    public Appointment requestAppointmentUpdateData(Appointment existing, Pets pet) {
        showMessage("\n--- Nuevos datos de la cita ---");
        LocalDateTime dateTime = dataTypeValidator.validateDateTime("Fecha y hora de la cita (dd/MM/yyyy HH:mm): ");
        String direction = dataTypeValidator.validateString("Dirección: ");
        String observations = dataTypeValidator.validateString("Observaciones: ");
        boolean state = confirm("¿La cita sigue activa? (s/n): ");

        return buildAppointment(existing.getIdAppointment(), dateTime, state, direction, observations, pet);
    }

    public void showAppointment(Appointment appointment) {
        showMessage("\n--- Cita ---");
        showMessage("Id: " + appointment.getIdAppointment());
        showMessage("Fecha: " + appointment.getDate());
        showMessage("Hora: " + appointment.getTime());
        showMessage("Estado: " + (appointment.getState() ? "Activa" : "Cancelada"));
        showMessage("Dirección: " + appointment.getDirection());
        showMessage("Observaciones: " + appointment.getObservations());
        showMessage("Mascota: " + appointment.getPet().getName());
        showMessage("Tutor: " + appointment.getPet().getTutor().getName()
                + " " + appointment.getPet().getTutor().getLastName());
    }

    public void showAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            showMessage("\nNo hay citas registradas.");
            return;
        }
        appointments.forEach(this::showAppointment);
    }

    public void showAppointmentNotFound(int id) {
        showMessage("No se encontró una cita con el ID: " + id);
    }

    public boolean confirmAppointmentDeletion() {
        return confirm("¿Está seguro de que desea eliminar la cita? (s/n): ");
    }

    private Appointment buildAppointment(int id, LocalDateTime dateTime, boolean state,
                                         String direction, String observations, Pets pet) {
        Date date = java.sql.Date.valueOf(dateTime.toLocalDate());
        Time time = Time.valueOf(dateTime.toLocalTime());
        return new Appointment(id, date, time, state, direction, observations, pet);
    }

    private boolean confirm(String prompt) {
        while (true) {
            String answer = dataTypeValidator.validateString(prompt).toLowerCase();
            if (answer.equals("s") || answer.equals("si") || answer.equals("sí")) {
                return true;
            }
            if (answer.equals("n") || answer.equals("no")) {
                return false;
            }
            showMessage("Responda con s o n.");
        }
    }
}