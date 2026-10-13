package app.domain.enums;

public enum PetSexEnum {

    MALE("Macho", true),
    FEMALE("Hembra", false);

    private final String sex;
    private final boolean value;

    PetSexEnum(String sex, boolean value) {
        this.sex = sex;
        this.value = value;
    }

    public String getSex() {
        return sex;
    }

    public boolean getValue() {
        return value;
    }
}