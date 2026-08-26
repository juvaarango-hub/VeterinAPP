package domain;

public class Animal {

    //Declarar variables
    protected Integer id;
    protected String name;
    protected String species;
    protected String breed;
    protected boolean sex;

    public Animal (){

    }

    public Animal(int id, String name, String species, String breed, boolean sex) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    // Metodos

    public void createAnimal(){

    }

    public void selectAllAnimal(){

    }

    public void selectAnimalById(int id){

    }

    public void updateAnimal(){

    }

    public void deleteAnimal(int id){

    }
}
