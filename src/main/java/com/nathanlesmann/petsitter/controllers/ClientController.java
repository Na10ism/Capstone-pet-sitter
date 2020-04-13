package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PetService petService;

    @RequestMapping(value = "/clients")
    public String getAllClients(Model model) {
        List<Client> theClients = clientService.getAllClients();

        model.addAttribute("clients", theClients);

        return "clients/AllClients";
    }

    @RequestMapping(value = "/clients/showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind the form data
        Client client = new Client();
        model.addAttribute("client", client);

        return "clients/clientForm";
    }

    @RequestMapping(value = "/clients/{id}")
    public String getClientById(@PathVariable int id, Model model) {

        Client client = clientService.getClientById(id).orElse(null);

        model.addAttribute("client", client);

        assert client != null;
        model.addAttribute("address", clientService.getAddressById(client.getIdFromAddress_id()));


        //create getAllPetsByClientId
        model.addAttribute("pets", petService.getAllPetsByClientId(client.getClient_id()));


        return "clients/singleClient";
    }





}

