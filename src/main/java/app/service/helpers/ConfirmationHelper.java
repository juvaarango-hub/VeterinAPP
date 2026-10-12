package app.service.helpers;

import app.domain.enums.ConfirmationEnum;
import app.service.validators.DataTypeValidator;

public class ConfirmationHelper {

    private final DataTypeValidator dataTypeValidator;

    public ConfirmationHelper(DataTypeValidator dataTypeValidator) {
        this.dataTypeValidator = dataTypeValidator;
    }

    public boolean confirm(String message) {

        while (true) {
            System.out.println("\n" + message);

            ConfirmationEnum[] options = ConfirmationEnum.values();

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i].getDescription());
            }

            int option = dataTypeValidator.validateInt("Seleccione una opción: ");

            if (option >= 1 && option <= options.length) {
                return options[option - 1].isConfirmed();
            }

            System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}