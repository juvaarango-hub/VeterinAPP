package app.service.inputports;

import app.domain.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment registerAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int id);
    Appointment updateAppointment(Appointment appointment);
    boolean existsById(int id);
    boolean deleteAppointment(int id);
}