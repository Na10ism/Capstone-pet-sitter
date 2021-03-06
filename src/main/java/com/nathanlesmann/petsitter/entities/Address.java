package com.nathanlesmann.petsitter.entities;

import javax.persistence.*;

@Entity
@Table(name="Address")
public class Address {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int address_id;
    private String street_address;
    private String city;
    private String state;
    private String zipcode;

//    @OneToOne(mappedBy = "address_id")
//    private Client client_id;


    public Address(){}


    public Address(String street_address, String city, String state, String zipcode) {
        this.street_address = street_address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }


    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return city + ", " + state;
    }
}
