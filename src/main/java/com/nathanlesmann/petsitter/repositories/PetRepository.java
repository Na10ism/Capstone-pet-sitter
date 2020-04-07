package com.nathanlesmann.petsitter.repositories;

import com.nathanlesmann.petsitter.entities.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


// create pet service to go with this interface
public interface PetRepository extends CrudRepository<Pet, Integer> {
//    Page<Pet> findClientId(int client_id, Pageable pageable);
//    Optional<Pet> findByIdAndClientId(int id, int client_id);
    @Query("select s from Pet s where client_id= :client_id ")
    public List<Pet> getAllPetsByClientId(int client_id);



    }
