package domain;

import java.sql.Time;
import java.util.Date;

public class Pay {

    private Integer idPay;
    private Date date;
    private Time time;
    private String payMethod;
    private String state;

    public Pay(){

    }

    public Pay(Integer idPay, Date date, Time time, String payMethod, String state) {
        this.idPay = idPay;
        this.date = date;
        this.time = time;
        this.payMethod = payMethod;
        this.state = state;
    }

    public Integer getIdPay() {
        return idPay;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getState() {
        return state;
    }

    public void setIdPay(Integer idPay) {
        this.idPay = idPay;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setState(String state) {
        this.state = state;
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
