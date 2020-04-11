package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.AddressService;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
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

        assert client != null;
        Address address = addressService.getAddressById(client.getIdFromAddress_id()).orElse(null);

        model.addAttribute("client", client);
        model.addAttribute("address", address);

        return "update/updateClientForm";

    }

    @RequestMapping(value = "/saveClient/{client_id}/{address_id}")
    public String saveUpdatedClient(@ModelAttribute("client") Client client,@PathVariable int address_id, @PathVariable int client_id, Model model) {


        Address address = addressService.getAddressById(address_id).orElse(null);
        client.setAddress_id(address);

        clientService.updateClientById(client_id, client);

        model.addAttribute("address", client.getAddress_id());

        return "update/updateAddressForm";
    }


    @RequestMapping(value = "/saveAddress/{address_id}")
    public String saveUpdatedAddress(@ModelAttribute("address") Address address,@PathVariable int address_id) {

        assert address != null;
        addressService.updateAddressById(address_id, address);

        return "redirect:/clients";
    }
}
