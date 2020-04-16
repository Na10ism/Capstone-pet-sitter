package com.nathanlesmann.petsitter.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

@Entity
@Table(name="Appointment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "client_id")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int appointment_id;

    private String apt_start_date;

    private String apt_end_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    public Client client;

    private String time;

    private String notes;


    public Appointment(){

    }

    public Appointment(String apt_start_date, String apt_end_date, String time, Client client, String notes){
        this.apt_start_date = apt_start_date;
        this.apt_end_date = apt_end_date;
        this.time = time;
        this.client = client;
        this.notes = notes;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public String getApt_start_date() {
        return apt_start_date;
    }

    public String getApt_end_date() {
        return apt_end_date;
    }

    public Client getClient() {
        return client;
    }

    public String getTime() {
        return time;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setApt_start_date(String apt_start_date) {
        this.apt_start_date = apt_start_date;
    }

    public void setApt_end_date(String apt_end_date) {
        this.apt_end_date = apt_end_date;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", start_date='" + apt_start_date + '\'' +
                ", end_date='" + apt_end_date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
