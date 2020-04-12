package com.nathanlesmann.petsitter.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Appointment")
public class Appointment {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int appointment_id;

    private String start_date;

    private String end_date;

    private Client client_id;

    private String time;


    public Appointment(){

    }

    public Appointment(String start_date, String end_date, String time){
        this.start_date = start_date;
        this.end_date = end_date;
        this.time = time;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public Client getClient_id() {
        return client_id;
    }

    public String getTime() {
        return time;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
