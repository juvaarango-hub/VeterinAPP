package domain;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    private Integer idAppointment;
    private Date date;
    private Time time;
    private Boolean state;
    private String direction;
    private String observations;

    public Appointment(){

    }

    public Appointment(Integer idAppointment, Date date, Time time, Boolean state, String direction, String observations) {
        this.idAppointment = idAppointment;
        this.date = date;
        this.time = time;
        this.state = state;
        this.direction = direction;
        this.observations = observations;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public Boolean getState() {
        return state;
    }

    public String getDirection() {
        return direction;
    }

    public String getObservations() {
        return observations;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void create(){

    }

    public void selectAll(){

    }

    public void selectById(int id){

    }

    public void deleteById(int id){

    }

    public void selectState(int id){

    }

}
