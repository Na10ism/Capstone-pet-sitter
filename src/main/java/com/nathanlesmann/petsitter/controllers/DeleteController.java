package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.entities.Pet;
import com.nathanlesmann.petsitter.services.AddressService;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class DeleteController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PetService petService;

    @RequestMapping(value = "/deleteClient/{client_id}")
    public String deleteClient(@PathVariable int client_id) {

        List<Pet> pets = petService.getAllPetsByClientId(client_id);

        for (Pet pet: pets) {
                petService.deletePet(pet.getPet_id());
        }
        clientService.deleteClient(client_id);

        return "redirect:/clients";
    }

    @RequestMapping(value = "/deletePet/{client_id}/{pet_id}")
    public String deletePet(@PathVariable int pet_id, @PathVariable int client_id) {

        petService.deletePet(pet_id);

        return "redirect:/clients/" + client_id;
    }




}
