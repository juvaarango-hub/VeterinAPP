package app.service;

import app.domain.Appointment;
import app.service.inputports.AppointmentService;
import app.service.outputports.AppointmentRepository;
import app.service.validators.AppointmentValidator;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentValidator appointmentValidator;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentValidator appointmentValidator) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentValidator = appointmentValidator;
    }

    @Override
    public Appointment registerAppointment(Appointment appointment) {
        appointmentValidator.validate(appointment);
        if (existsById(appointment.getIdAppointment())) {
            throw new IllegalArgumentException("Ya existe una cita con ese id.");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        appointmentValidator.validate(appointment);
        return appointmentRepository.update(appointment);
    }

    @Override
    public boolean existsById(int id) {
        return appointmentRepository.findById(id) != null;
    }

    @Override
    public boolean deleteAppointment(int id) {
        return appointmentRepository.deleteById(id);
    }
}