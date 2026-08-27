package domain;

public class Tutor extends Person{
    private String direction;

    public Tutor(String direction) {
        this.direction = direction;
    }

    public Tutor(Integer id, String name, String lastName, String email, String phone, String password, boolean state, String direction) {
        super(id, name, lastName, email, phone, password, state);
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

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
