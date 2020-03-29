package com.nathanlesmann.petsitter.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Client")
public class Client {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private int client_id;
    private String client_first_name;
    private String client_last_name;
    private String client_phone_number;
    private String client_email;

    @OneToMany(
            mappedBy = "client_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pet> pets = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Address address_id;

    public Client(){

    }

    public Client(String client_first_name, String client_last_name, String client_phone_number, String client_email, Address address_id) {
        this.client_first_name = client_first_name;
        this.client_last_name = client_last_name;
        this.client_phone_number = client_phone_number;
        this.client_email = client_email;
        this.address_id = address_id;
    }

    public Address getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Address address_id) {
        this.address_id = address_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_first_name() {
        return client_first_name;
    }

    public void setClient_first_name(String client_first_name) {
        this.client_first_name = client_first_name;
    }

    public String getClient_last_name() {
        return client_last_name;
    }

    public void setClient_last_name(String client_last_name) {
        this.client_last_name = client_last_name;
    }

    public String getClient_phone_number() {
        return client_phone_number;
    }

    public void setClient_phone_number(String client_phone_number) {
        this.client_phone_number = client_phone_number;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + client_id +
                ", client_first_name='" + client_first_name + '\'' +
                ", client_last_name='" + client_last_name + '\'' +
                ", client_phone_number='" + client_phone_number + '\'' +
                ", client_email='" + client_email + '\'' +
                ", address_id=" + address_id +
                '}';
    }
}
