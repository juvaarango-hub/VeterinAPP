package domain;

public class ClinicHistory {

    private Integer idClinicHistory;
    private String date;
    private Pets pets;
    private DoctorVet doctorVet;
    private String diagnosis;
    private String treatmen;
    private String observations;

    public ClinicHistory(){

    }

    public ClinicHistory(Integer idClinicHistory, String date, Pets pets, DoctorVet doctorVet, String diagnosis, String treatmen, String observations) {
        this.idClinicHistory = idClinicHistory;
        this.date = date;
        this.pets = pets;
        this.doctorVet = doctorVet;
        this.diagnosis = diagnosis;
        this.treatmen = treatmen;
        this.observations = observations;
    }

    public Integer getIdClinicHistory() {
        return idClinicHistory;
    }

    public String getDate() {
        return date;
    }

    public Pets getPets() {
        return pets;
    }

    public DoctorVet getDoctorVet() {
        return doctorVet;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatmen() {
        return treatmen;
    }

    public String getObservations() {
        return observations;
    }


    public void setIdClinicHistory(Integer idClinicHistory) {
        this.idClinicHistory = idClinicHistory;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPets(Pets pets) {
        this.pets = pets;
    }

    public void setDoctorVet(DoctorVet doctorVet) {
        this.doctorVet = doctorVet;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatmen(String treatmen) {
        this.treatmen = treatmen;
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
