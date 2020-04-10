package com.nathanlesmann.petsitter.services;

import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.entities.Pet;
import com.nathanlesmann.petsitter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    public PetRepository petRepository;



    public List<Pet> getAllPets() {

        List<Pet> pets = new ArrayList<>();

        petRepository.findAll()
                .forEach(pets::add);

        return pets;
    }

    public void addPet(Pet pet) {

        petRepository.save(pet);
    }

    public Optional<Pet> getPet(int id) {
        return petRepository.findById(id);
    }

    public void updateOrAddPet(Pet pet) {
        petRepository.save(pet);
    }

    public void deletePet(int id) {
        petRepository.deleteById(id);
    }

    public List<Pet> getAllPetsByClientId(int client_id) {
        return petRepository.getAllPetsByClientId(client_id);
    }


}
