package app.configuration;

import app.repository.PetsRepositoryImplCollection;
import app.repository.TutorRepositoryImplCollection;

import app.service.PetsServiceImpl;
import app.service.TutorServiceImpl;

import app.service.inputports.PetsService;
import app.service.inputports.TutorService;

import app.service.outputports.PetsRepository;
import app.service.outputports.TutorRepository;

import app.service.validators.DataTypeValidator;
import app.service.validators.TutorValidator;

import app.service.helpers.SelectPetSex;
import app.service.helpers.ConfirmationHelper;

import app.ui.CliVeterinAppInterface;
import app.ui.PetsUI;
import app.ui.TutorUI;

import app.view.PetsView;
import app.view.TutorView;

import java.util.Scanner;

public class Config {

    public static CliVeterinAppInterface getCliVeterinAppInterface() {

        Scanner scanner = new Scanner(System.in);

        // DEPENDENCIAS COMPARTIDAS
        DataTypeValidator dataTypeValidator = new DataTypeValidator(scanner);

        // MÓDULO DE TUTORES
        TutorRepository tutorRepository = new TutorRepositoryImplCollection();
        TutorValidator tutorValidator = new TutorValidator();
        TutorService tutorService = new TutorServiceImpl(tutorRepository, tutorValidator);
        ConfirmationHelper confirmationHelper = new ConfirmationHelper(dataTypeValidator);
        TutorView tutorView = new TutorView(tutorValidator, dataTypeValidator, confirmationHelper);
        TutorUI tutorUI = new TutorUI(tutorService, tutorView);

        // MÓDULO DE MASCOTAS
        PetsRepository petsRepository = new PetsRepositoryImplCollection();
        PetsService petsService = new PetsServiceImpl(petsRepository);
        SelectPetSex selectPetSex = new SelectPetSex(dataTypeValidator);
        PetsView petsView = new PetsView(dataTypeValidator, selectPetSex, confirmationHelper);
        PetsUI petsUI = new PetsUI(petsService, tutorService, petsView, tutorView);

        // INTERFAZ PRINCIPAL
        return new CliVeterinAppInterface(tutorUI, petsUI, scanner);
    }
}
