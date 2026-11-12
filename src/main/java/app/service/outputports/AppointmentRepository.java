package app.service.outputports;

import app.domain.Appointment;
import java.util.List;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    List<Appointment> findAll();
    Appointment findById(int id);
    Appointment update(Appointment appointment);
    boolean deleteById(int id);
}