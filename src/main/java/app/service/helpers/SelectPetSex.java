package app.service.helpers;

import app.domain.enums.PetSexEnum;
import app.service.validators.DataTypeValidator;

public class SelectPetSex {

    private final DataTypeValidator dataTypeValidator;

    public SelectPetSex(DataTypeValidator dataTypeValidator) {
        this.dataTypeValidator = dataTypeValidator;
    }

    public PetSexEnum getPetSex() {

        while (true) {
            System.out.println("\nSeleccione el sexo de la mascota:");

            PetSexEnum[] options = PetSexEnum.values();

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i].getSex());
            }

            int option = dataTypeValidator.validateInt("Seleccione una opción: ");

            if (option >= 1 && option <= options.length) {
                return options[option - 1];
            }

            System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}