package domain;

import java.time.format.DateTimeFormatter;


public class ClinicHistory {

    private Integer idAppointment;
    private DateTimeFormatter date;
    private String subjective;
    private String objective;
    private String interpretation;
    private String plan;
    private String observation;
    private Doctor doctor;

    public ClinicHistory(){

    }

    public ClinicHistory(Integer idAppointment, DateTimeFormatter date, String subjective, String objective, String interpretation, String plan, String observation, Doctor doctor) {
        this.idAppointment = idAppointment;
        this.date = date;
        this.subjective = subjective;
        this.objective = objective;
        this.interpretation = interpretation;
        this.plan = plan;
        this.observation = observation;
        this.doctor = doctor;
    }

    public Integer getIdAppointment() {
        return idAppointment;
    }

    public DateTimeFormatter getDate() {
        return date;
    }

    public String getSubjective() {
        return subjective;
    }

    public String getObjective() {
        return objective;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public String getPlan() {
        return plan;
    }

    public String getObservation() {
        return observation;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setIdAppointment(Integer idAppointment) {
        this.idAppointment = idAppointment;
    }

    public void setDate(DateTimeFormatter date) {
        this.date = date;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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