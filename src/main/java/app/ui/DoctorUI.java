package app.ui;

import app.domain.Doctor;
import app.service.inputports.DoctorService;
import app.service.validators.DataTypeValidator;
import app.view.DoctorView;
import java.util.List;

public class DoctorUI {

    private final DoctorService doctorService;
    private final DoctorView doctorView;
    private final DataTypeValidator dataTypeValidator;

    public DoctorUI(DoctorService doctorService, DoctorView doctorView,DataTypeValidator dataTypeValidator ) {
        this.doctorService = doctorService;
        this.doctorView = doctorView;
        this.dataTypeValidator = dataTypeValidator;
    }

    public void handleDoctorMenu() {
        int option = 0;
        while (option != 6) {
            System.out.println("\n--- GESTIÓN DE DOCTORES ---");
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Consultar Doctor por ID");
            System.out.println("3. Listar todos los Doctores");
            System.out.println("4. Actualizar Doctor");
            System.out.println("5. Eliminar Doctor");
            System.out.println("6. Volver al Menú Principal");

            option = dataTypeValidator.validateInt("Seleccione una opción: ");

            switch (option) {
                case 1:
                    int id = doctorView.requestDoctorId();
                    if (doctorService.existsById(id)) {
                        doctorView.showMessage("Error: Ya existe un doctor registrado con esa identificación.");
                    } else {
                        Doctor newDoctor = doctorView.requestDoctorData(id);
                        doctorService.registerDoctor(newDoctor);
                        doctorView.showMessage("¡Doctor registrado exitosamente!");
                    }
                    break;
                case 2:
                    int searchId = doctorView.requestDoctorId();
                    Doctor doctor = doctorService.getDoctorById(searchId);
                    if (doctor != null) {
                        doctorView.showDoctor(doctor);
                    } else {
                        doctorView.showDoctorNotFound(searchId);
                    }
                    break;
                case 3:
                    doctorView.showDoctors(doctorService.getAllDoctors());
                    break;
                case 4:
                    int updateId = doctorView.requestDoctorId();
                    Doctor existing = doctorService.getDoctorById(updateId);
                    if (existing != null) {
                        Doctor updated = doctorView.requestDoctorUpdate(existing);
                        doctorService.updateDoctor(updated);
                        doctorView.showMessage("¡Doctor actualizado con éxito!");
                    } else {
                        doctorView.showDoctorNotFound(updateId);
                    }
                    break;
                case 5:
                    int deleteId = doctorView.requestDoctorId();
                    if (doctorService.existsById(deleteId)) {
                        if (doctorView.requestDeleteConfirmation()) {
                            doctorService.deleteDoctor(deleteId);
                            doctorView.showMessage("Doctor eliminado correctamente.");
                        }
                    } else {
                        doctorView.showDoctorNotFound(deleteId);
                    }
                    break;
                case 6:
                    System.out.println("Regresando...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}


