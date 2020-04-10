package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.AddressService;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/update")
public class UpdateController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PetService petService;

    @Autowired
    private AddressService addressService;


    @RequestMapping(value = "/updateClient/{client_id}")
    public String updateClientForm(@PathVariable int client_id, Model model) {

        // create model attribute to bind the form data
        Client client = clientService.getClientById(client_id).orElse(null);

        model.addAttribute("client", client);

        return "update/updateClient";

    }

    @RequestMapping(value = "/saveClient/{client_id}")
    public String saveUpdatedClient(@PathVariable int client_id) {
        Client client = clientService.getClientById(client_id).orElse(null);

        clientService.updateClientById(client_id, client);

        return "update/updateAddress";
    }

    @RequestMapping(value = "/updateAddress/{address_id}")
    public void updateAddressForm(@PathVariable int address_id, Model model) {

        Address address = addressService.getAddress(address_id).orElse(null);
        addressService.updateAddress(address_id, address);
    }
}
