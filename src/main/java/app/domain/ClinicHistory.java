package app.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ClinicHistory {

    private Appointment appointment;
    private LocalDateTime date;
    private String subjective;
    private String objective;
    private String interpretation;
    private String plan;
    private String observation;
    private Doctor doctor;

    public ClinicHistory(Appointment appointment, LocalDateTime date, String subjective,
                         String objective, String interpretation, String plan,
                         String observation, Doctor doctor) {
        this.appointment = appointment;
        this.date = date;
        this.subjective = subjective;
        this.objective = objective;
        this.interpretation = interpretation;
        this.plan = plan;
        this.observation = observation;
        this.doctor = doctor;
    }

    public Appointment getAppointment() { return appointment; }
    public LocalDateTime getDate() { return date; }
    public String getSubjective() { return subjective; }
    public String getObjective() { return objective; }
    public String getInterpretation() { return interpretation; }
    public String getPlan() { return plan; }
    public String getObservation() { return observation; }
    public Doctor getDoctor() { return doctor; }

    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setSubjective(String subjective) { this.subjective = subjective; }
    public void setObjective(String objective) { this.objective = objective; }
    public void setInterpretation(String interpretation) { this.interpretation = interpretation; }
    public void setPlan(String plan) { this.plan = plan; }
    public void setObservation(String observation) { this.observation = observation; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

}