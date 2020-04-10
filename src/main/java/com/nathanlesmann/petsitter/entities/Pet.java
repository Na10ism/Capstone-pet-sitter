package com.nathanlesmann.petsitter.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name="Pet")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "client_id")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pet_id;

    private String pet_type;

    private String pet_name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    private Client client;

    public Pet(){}

    public Pet(String pet_type, String pet_name, Client client) {
        this.pet_type = pet_type;
        this.pet_name = pet_name;
        this.client = client;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_type() {
        return pet_type;
    }

    public void setPet_type(String pet_type) {
        this.pet_type = pet_type;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client_id) {
        this.client = client_id;
    }

    public int getClient_id(){
        return client.getClient_id();
    }



    @Override
    public String toString() {
        return "Pet{" +
                "pet_id=" + pet_id +
                ", pet_type='" + pet_type + '\'' +
                ", pet_name='" + pet_name;
    }
}
