package app.configuration;

import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.repository.*;

import app.service.*;

import app.service.inputports.*;

import app.service.outputports.*;

import app.service.validators.*;

import app.service.helpers.SelectPetSex;
import app.service.helpers.ConfirmationHelper;

import app.ui.*;

import app.view.*;

import java.util.Scanner;

public class Config {

    public static CliVeterinAppInterface getCliVeterinAppInterface() {

        Scanner scanner = new Scanner(System.in);

        // DEPENDENCIAS COMPARTIDAS
        DataTypeValidator dataTypeValidator = new DataTypeValidator(scanner);
        DataBaseConnection dbConnection = new DataBaseConnection();

        // MÓDULO DE TUTORES

        TutorRepository tutorRepository = new TutorRepositoryAdapterDB(dbConnection);
        TutorValidator tutorValidator = new TutorValidator();
        TutorService tutorService = new TutorServiceImpl(tutorRepository, tutorValidator);
        ConfirmationHelper confirmationHelper = new ConfirmationHelper(dataTypeValidator);
        TutorView tutorView = new TutorView(tutorValidator, dataTypeValidator, confirmationHelper);
        TutorUI tutorUI = new TutorUI(tutorService, tutorView, dataTypeValidator);


        // MÓDULO DE MASCOTAS
        PetsRepository petsRepository = new PetsRepositoryAdapterDB(dbConnection, tutorRepository);
        //PetsRepository petsRepository = new PetsRepositoryImplCollection();
        PetsService petsService = new PetsServiceImpl(petsRepository);
        SelectPetSex selectPetSex = new SelectPetSex(dataTypeValidator);
        PetsView petsView = new PetsView(dataTypeValidator, selectPetSex, confirmationHelper);
        PetsUI petsUI = new PetsUI(petsService, tutorService, petsView, tutorView, dataTypeValidator);


        // MÓDULO DE CITAS
        AppointmentRepository appointmentRepository = new AppointmentRepositoryAdapterDB(dbConnection, petsRepository);
        AppointmentValidator appointmentValidator = new AppointmentValidator();
        AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository, appointmentValidator);
        AppointmentView appointmentView = new AppointmentView(dataTypeValidator, confirmationHelper);
        AppointmentUI appointmentUI = new AppointmentUI(appointmentService, petsService, appointmentView, petsView, dataTypeValidator);


        // MÓDULO DE DOCTORES
        DoctorRepository doctorRepository = new DoctorRepositoryAdapterDB(dbConnection);
        DoctorValidator doctorValidator = new DoctorValidator();
        DoctorService doctorService = new DoctorServiceImpl(doctorRepository, doctorValidator);
        DoctorView doctorView = new DoctorView(doctorValidator, dataTypeValidator, confirmationHelper);
        DoctorUI doctorUI = new DoctorUI(doctorService, doctorView, dataTypeValidator);

        // MÓDULO DE HISTORIAS CLÍNICAS
        ClinicHistoryRepository clinicHistoryRepository = new ClinicHistoryRepositoryAdapterDB(dbConnection, appointmentRepository, doctorRepository);
        ClinicHistoryValidator clinicHistoryValidator = new ClinicHistoryValidator();
        ClinicHistoryService clinicHistoryService = new ClinicHistoryServiceImpl(clinicHistoryRepository, clinicHistoryValidator);
        ClinicHistoryView clinicHistoryView = new ClinicHistoryView(dataTypeValidator, confirmationHelper);
        ClinicHistoryUI clinicHistoryUI = new ClinicHistoryUI(clinicHistoryService, appointmentService, petsService, doctorService, clinicHistoryView, appointmentView, petsView, dataTypeValidator);

        // INTERFAZ PRINCIPAL
        return new CliVeterinAppInterface(tutorUI, petsUI, doctorUI, appointmentUI, clinicHistoryUI, scanner);

    }
}
