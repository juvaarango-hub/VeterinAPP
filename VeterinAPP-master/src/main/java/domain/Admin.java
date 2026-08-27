package domain;

public class Admin extends Person{

        private String rol;
        private String area;

        public Admin(){

            super();
        }

    public Admin(Integer id, String name, String lastName, String email, String phone, String password, boolean state, String rol, String area) {
        super(id, name, lastName, email, phone, password, state);
        this.rol = rol;
        this.area = area;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
