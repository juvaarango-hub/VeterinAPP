package domain;

public class Doctor extends Person{

    private Integer idMp;
    private String specialty;

    public Doctor (){
        super();

    }

    public Doctor(int idMp, String specialty) {
        this.idMp = idMp;
        this.specialty = specialty;
    }

    public Doctor(int id, String name, String lastName, String email, String phone, String password, boolean state, int idMp, String specialty) {
        super(id, name, lastName, email, phone, password, state);
        this.idMp = idMp;
        this.specialty = specialty;
    }

    public int getIdMp() {
        return idMp;
    }

    public void setIdMp(int idMp) {
        this.idMp = idMp;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

//Metodos

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void selectAll() {
        super.selectAll();
    }

    @Override
    public void selectById(int id) {
        super.selectById(id);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }
}
