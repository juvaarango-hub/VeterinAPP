package app.domain.enums;

public enum ConfirmationEnum {

    YES("Sí, confirmar", true),
    NO("No, cancelar", false);

    private final String description;
    private final boolean confirmed;

    ConfirmationEnum(String description, boolean confirmed) {
        this.description = description;
        this.confirmed = confirmed;
    }

    public String getDescription() {
        return description;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}