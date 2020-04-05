package com.nathanlesmann.petsitter.repositories;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    @Query("select s from Address s where address_id= :address_id ")
    Address getAddress(int address_id);



}
