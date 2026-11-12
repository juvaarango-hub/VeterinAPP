package app.ui;

import app.domain.Appointment;
import app.domain.Pets;
import app.service.inputports.AppointmentService;
import app.service.inputports.PetsService;
import app.service.validators.DataTypeValidator;
import app.view.AppointmentView;
import app.view.PetsView;

import java.util.List;

public class AppointmentUI {

    private final AppointmentService appointmentService;
    private final PetsService petsService;
    private final AppointmentView appointmentView;
    private final PetsView petsView;
    private final DataTypeValidator dataTypeValidator;

    public AppointmentUI(AppointmentService appointmentService, PetsService petsService,
                         AppointmentView appointmentView, PetsView petsView,
                         DataTypeValidator dataTypeValidator) {
        this.appointmentService = appointmentService;
        this.petsService = petsService;
        this.appointmentView = appointmentView;
        this.petsView = petsView;
        this.dataTypeValidator = dataTypeValidator;
    }

    public void handleAppointmentMenu() {
        int option;

        do {
            System.out.println("\n--- GESTIÓN DE CITAS ---");
            System.out.println("1. Registrar cita");
            System.out.println("2. Buscar cita por ID");
            System.out.println("3. Listar citas");
            System.out.println("4. Actualizar cita");
            System.out.println("5. Eliminar cita");
            System.out.println("0. Volver al menú principal");

            option = dataTypeValidator.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1:
                    registerAppointment();
                    break;
                case 2:
                    findAppointmentById();
                    break;
                case 3:
                    listAppointments();
                    break;
                case 4:
                    updateAppointment();
                    break;
                case 5:
                    deleteAppointment();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    public void registerAppointment() {
        appointmentView.showMessage("\n--- Registro de cita ---");
        appointmentView.showMessage("Ingrese el ID de la mascota para la cual es la cita.");
        int petId = petsView.requestPetId();

        Pets pet = petsService.getPetById(petId);

        if (pet == null) {
            petsView.showPetNotFound(petId);
            return;
        }

        try {
            Appointment appointment = appointmentView.requestAppointmentData(pet);
            Appointment registered = appointmentService.registerAppointment(appointment);

            appointmentView.showMessage("Cita registrada correctamente.");
            appointmentView.showAppointment(registered);

        } catch (RuntimeException e) {
            appointmentView.showMessage("Error al registrar la cita: " + e.getMessage());
        }
    }

    public void findAppointmentById() {
        appointmentView.showMessage("\n--- Consulta de cita ---");

        int id = appointmentView.requestAppointmentId();

        Appointment appointment = appointmentService.getAppointmentById(id);

        if (appointment == null) {
            appointmentView.showAppointmentNotFound(id);
            return;
        }

        appointmentView.showAppointment(appointment);
    }

    public void listAppointments() {
        appointmentView.showMessage("\n--- Listado de citas ---");

        List<Appointment> appointments = appointmentService.getAllAppointments();

        appointmentView.showAppointments(appointments);
    }

    public void updateAppointment() {
        appointmentView.showMessage("\n--- Actualizar cita ---");

        int id = appointmentView.requestAppointmentId();

        Appointment existing = appointmentService.getAppointmentById(id);

        if (existing == null) {
            appointmentView.showAppointmentNotFound(id);
            return;
        }

        appointmentView.showMessage("\nMascota actual: " + existing.getPet().getName());
        appointmentView.showMessage("Ingrese el ID de la mascota para la cita.");

        int petId = petsView.requestPetId();

        Pets pet = petsService.getPetById(petId);

        if (pet == null) {
            petsView.showPetNotFound(petId);
            return;
        }

        try {
            Appointment updatedData = appointmentView.requestAppointmentUpdateData(existing, pet);
            Appointment updated = appointmentService.updateAppointment(updatedData);

            appointmentView.showMessage("Cita actualizada correctamente.");
            appointmentView.showAppointment(updated);

        } catch (RuntimeException e) {
            appointmentView.showMessage("Error al actualizar la cita: " + e.getMessage());
        }
    }

    public void deleteAppointment() {
        appointmentView.showMessage("\n--- Eliminar cita ---");

        int id = appointmentView.requestAppointmentId();

        Appointment appointment = appointmentService.getAppointmentById(id);

        if (appointment == null) {
            appointmentView.showAppointmentNotFound(id);
            return;
        }

        appointmentView.showAppointment(appointment);

        if (!appointmentView.confirmAppointmentDeletion()) {
            appointmentView.showMessage("Eliminación cancelada.");
            return;
        }

        try {
            boolean deleted = appointmentService.deleteAppointment(id);

            if (deleted) {
                appointmentView.showMessage("Cita eliminada correctamente.");
            } else {
                appointmentView.showMessage("No fue posible eliminar la cita.");
            }
        } catch (RuntimeException e) {
            appointmentView.showMessage("Error al eliminar la cita: " + e.getMessage());
        }
    }
}