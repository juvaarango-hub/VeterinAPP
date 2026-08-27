package domain;

import java.time.LocalDate;

public class Pets extends Animal{

    private Integer weight;
    private LocalDate age;
    private Tutor tutor;


    public Pets(){
        super();
    }

    public Pets(int id, String name, String species, String breed, boolean sex, Integer weight, LocalDate age, Tutor tutor) {
        super(id, name, species, breed, sex);
        this.weight = weight;
        this.age = age;
        this.tutor = tutor;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDate getAge() {
        return age;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    // Metodos



    public void createAnimal(){
        super.createAnimal();

    }
    @Override
    public void selectAllAnimal(){
        super.selectAllAnimal();

    }
    @Override
    public void selectAnimalById(int id){
        super.selectAnimalById(id);

    }
    @Override
    public void updateAnimal(){
        super.updateAnimal();

    }
    @Override
    public void deleteAnimal(int id){
        super.deleteAnimal(id);

    }
}
