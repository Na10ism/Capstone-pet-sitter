package com.nathanlesmann.petsitter.entities;

import javax.persistence.*;

@Entity
@Table(name="Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pet_id;

    private String pet_type;

    private String pet_name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client_id;


}
