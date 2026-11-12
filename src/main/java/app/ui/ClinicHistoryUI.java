package app.ui;

import app.domain.Appointment;
import app.domain.ClinicHistory;
import app.domain.Doctor;
import app.domain.Pets;
import app.service.inputports.AppointmentService;
import app.service.inputports.ClinicHistoryService;
import app.service.inputports.DoctorService;
import app.service.inputports.PetsService;
import app.service.validators.DataTypeValidator;
import app.view.AppointmentView;
import app.view.ClinicHistoryView;
import app.view.PetsView;

import java.util.List;

public class ClinicHistoryUI {

    private final ClinicHistoryService clinicHistoryService;
    private final AppointmentService appointmentService;
    private final PetsService petsService;
    private final DoctorService doctorService;
    private final ClinicHistoryView clinicHistoryView;
    private final AppointmentView appointmentView;
    private final PetsView petsView;
    private final DataTypeValidator dataTypeValidator;

    public ClinicHistoryUI(ClinicHistoryService clinicHistoryService,
                           AppointmentService appointmentService,
                           PetsService petsService,
                           DoctorService doctorService,
                           ClinicHistoryView clinicHistoryView,
                           AppointmentView appointmentView,
                           PetsView petsView,
                           DataTypeValidator dataTypeValidator) {
        this.clinicHistoryService = clinicHistoryService;
        this.appointmentService = appointmentService;
        this.petsService = petsService;
        this.doctorService = doctorService;
        this.clinicHistoryView = clinicHistoryView;
        this.appointmentView = appointmentView;
        this.petsView = petsView;
        this.dataTypeValidator = dataTypeValidator;
    }

    public void handleClinicHistoryMenu() {
        int option;

        do {
            System.out.println("\n--- GESTIÓN DE HISTORIAS CLÍNICAS ---");
            System.out.println("1. Registrar historia clínica (de una cita)");
            System.out.println("2. Ver historial clínico de una mascota");
            System.out.println("3. Buscar historia clínica por cita");
            System.out.println("4. Listar todas las historias clínicas");
            System.out.println("5. Actualizar historia clínica");
            System.out.println("6. Eliminar historia clínica");
            System.out.println("0. Volver al menú principal");

            option = dataTypeValidator.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1:
                    registerClinicHistory();
                    break;
                case 2:
                    showPetHistory();
                    break;
                case 3:
                    findByAppointmentId();
                    break;
                case 4:
                    listClinicHistories();
                    break;
                case 5:
                    updateClinicHistory();
                    break;
                case 6:
                    deleteClinicHistory();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Seleccione una opción válida.");
            }

        } while (option != 0);
    }

    public void registerClinicHistory() {
        clinicHistoryView.showMessage("\n--- Registro de historia clínica ---");
        clinicHistoryView.showMessage("Ingrese el ID de la cita que se atendió.");

        int appointmentId = appointmentView.requestAppointmentId();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            appointmentView.showAppointmentNotFound(appointmentId);
            return;
        }

        if (clinicHistoryService.existsById(appointmentId)) {
            clinicHistoryView.showMessage("Esa cita ya tiene una historia clínica registrada.");
            return;
        }

        int doctorId = clinicHistoryView.requestDoctorId();
        Doctor doctor = doctorService.getDoctorById(doctorId);

        if (doctor == null) {
            clinicHistoryView.showMessage("No se encontró un doctor con el ID: " + doctorId);
            return;
        }

        try {
            ClinicHistory history = clinicHistoryView.requestClinicHistoryData(appointment, doctor);
            ClinicHistory registered = clinicHistoryService.registerClinicHistory(history);

            clinicHistoryView.showMessage("Historia clínica registrada correctamente.");
            clinicHistoryView.showClinicHistory(registered);

        } catch (RuntimeException e) {
            clinicHistoryView.showMessage("Error al registrar la historia clínica: " + e.getMessage());
        }
    }

    public void showPetHistory() {
        clinicHistoryView.showMessage("\n--- Historial clínico de una mascota ---");

        int petId = petsView.requestPetId();
        Pets pet = petsService.getPetById(petId);

        if (pet == null) {
            petsView.showPetNotFound(petId);
            return;
        }

        try {
            List<ClinicHistory> histories = clinicHistoryService.getClinicHistoriesByPetId(petId);
            clinicHistoryView.showPetClinicHistories(pet, histories);
        } catch (RuntimeException e) {
            clinicHistoryView.showMessage("Error al consultar el historial: " + e.getMessage());
        }
    }

    public void findByAppointmentId() {
        clinicHistoryView.showMessage("\n--- Consulta de historia clínica ---");

        int appointmentId = appointmentView.requestAppointmentId();
        ClinicHistory history = clinicHistoryService.getClinicHistoryById(appointmentId);

        if (history == null) {
            clinicHistoryView.showClinicHistoryNotFound(appointmentId);
            return;
        }

        clinicHistoryView.showClinicHistory(history);
    }

    public void listClinicHistories() {
        clinicHistoryView.showMessage("\n--- Listado de historias clínicas ---");

        List<ClinicHistory> histories = clinicHistoryService.getAllClinicHistories();
        clinicHistoryView.showClinicHistories(histories);
    }

    public void updateClinicHistory() {
        clinicHistoryView.showMessage("\n--- Actualizar historia clínica ---");

        int appointmentId = appointmentView.requestAppointmentId();
        ClinicHistory existing = clinicHistoryService.getClinicHistoryById(appointmentId);

        if (existing == null) {
            clinicHistoryView.showClinicHistoryNotFound(appointmentId);
            return;
        }

        clinicHistoryView.showMessage("\nDoctor actual: " + existing.getDoctor().getName()
                + " " + existing.getDoctor().getLastName());

        int doctorId = clinicHistoryView.requestDoctorId();
        Doctor doctor = doctorService.getDoctorById(doctorId);

        if (doctor == null) {
            clinicHistoryView.showMessage("No se encontró un doctor con el ID: " + doctorId);
            return;
        }

        try {
            ClinicHistory updatedData = clinicHistoryView.requestClinicHistoryUpdateData(existing, doctor);
            ClinicHistory updated = clinicHistoryService.updateClinicHistory(updatedData);

            clinicHistoryView.showMessage("Historia clínica actualizada correctamente.");
            clinicHistoryView.showClinicHistory(updated);

        } catch (RuntimeException e) {
            clinicHistoryView.showMessage("Error al actualizar la historia clínica: " + e.getMessage());
        }
    }

    public void deleteClinicHistory() {
        clinicHistoryView.showMessage("\n--- Eliminar historia clínica ---");

        int appointmentId = appointmentView.requestAppointmentId();
        ClinicHistory history = clinicHistoryService.getClinicHistoryById(appointmentId);

        if (history == null) {
            clinicHistoryView.showClinicHistoryNotFound(appointmentId);
            return;
        }

        clinicHistoryView.showClinicHistory(history);

        if (!clinicHistoryView.confirmClinicHistoryDeletion()) {
            clinicHistoryView.showMessage("Eliminación cancelada.");
            return;
        }

        try {
            boolean deleted = clinicHistoryService.deleteClinicHistory(appointmentId);

            if (deleted) {
                clinicHistoryView.showMessage("Historia clínica eliminada correctamente.");
            } else {
                clinicHistoryView.showMessage("No fue posible eliminar la historia clínica.");
            }
        } catch (RuntimeException e) {
            clinicHistoryView.showMessage("Error al eliminar la historia clínica: " + e.getMessage());
        }
    }
}