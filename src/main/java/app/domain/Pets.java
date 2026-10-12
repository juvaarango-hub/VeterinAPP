package app.domain;

import java.time.LocalDate;
import java.time.Period;

public class Pets extends Animal {

    private double weight;
    private LocalDate birthDate;
    private Tutor tutor;

    public Pets() {
        super();
    }

    public Pets(
            int id,
            String name,
            String species,
            String breed,
            boolean sex,
            double weight,
            LocalDate birthDate,
            Tutor tutor
    ) {
        super(id, name, species, breed, sex);
        this.weight = weight;
        this.birthDate = birthDate;
        this.tutor = tutor;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Period getAge() {
        if (birthDate == null) {
            throw new IllegalStateException(
                    "La fecha de nacimiento no está registrada."
            );
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalStateException(
                    "La fecha de nacimiento no puede estar en el futuro."
            );
        }

        return Period.between(birthDate, LocalDate.now());
    }

    // Métodos heredados de Animal

    @Override
    public void createAnimal() {
        super.createAnimal();
    }

    @Override
    public void selectAllAnimal() {
        super.selectAllAnimal();
    }

    @Override
    public void selectAnimalById(int id) {
        super.selectAnimalById(id);
    }

    @Override
    public void updateAnimal() {
        super.updateAnimal();
    }

    @Override
    public void deleteAnimal(int id) {
        super.deleteAnimal(id);
    }
}
