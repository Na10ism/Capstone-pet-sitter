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

        model.addAttribute("client", client);

        return "update/updateClientForm";

    }

    @RequestMapping(value = "/saveClient/{client_id}")
    public String saveUpdatedClient(@PathVariable int client_id, Model model) {
        Client client = clientService.getClientById(client_id).orElse(null);

        clientService.updateClientById(client_id, client);

        model.addAttribute("client", client);

        return "update/updateAddress" + client_id;
    }

    @RequestMapping(value = "/updateAddress/{client_id}")
    public String updateAddressForm(@PathVariable int client_id, Model model) {

        Client client = clientService.getClientById(client_id).orElse(null);

        assert client != null;
        Address address = client.getAddress_id();

        model.addAttribute("address", address);
        model.addAttribute("client_id", client_id);

        return "update/updateAddressForm";
    }

    @RequestMapping(value = "/saveAddress/{client_id}")
    public String saveUpdatedAddress(@PathVariable int client_id) {

        Client client = clientService.getClientById(client_id).orElse(null);

        assert client != null;
        Address address = addressService.getAddressById(
                client.getIdFromAddress_id()).orElse(null);

        assert address != null;
        addressService.updateAddressById(address.getAddress_id(), address);


        return "clients/";
    }
}
