package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.entities.Pet;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PetController {


    @Autowired
    private PetService petService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value="/pets")
    public List<Pet> getAllPets(){

        return petService.getAllPets();
    }

    @RequestMapping(value="/pets/{pet_id}")
    public Optional<Pet> getPetById(@PathVariable("pet_id") int pet_id){
        return petService.getPet(pet_id);
    }

    @PostMapping("/pets/save")
    public void savePet(@ModelAttribute("pet") Pet thePet) {

        // save the employee
        petService.updateOrAddPet(thePet);

        // use a redirect to prevent duplicate submissions
//       return "redirect:/address/showFormForAddress";
    }

    @RequestMapping(value = "/pet/showFormForPet/{client_id}")
    public String showFormForPet(Model model, @PathVariable int client_id) {

        // create model attribute to bind the form data
        Pet pet = new Pet();

        Client client = clientService.getClient(client_id).orElse(null);

        model.addAttribute("pet", pet);

        return "clients/clientForm";
    }
}
