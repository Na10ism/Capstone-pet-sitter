package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients")
    public List<Client> getAllClients()
    {

        return clientService.getAllClients();
    }


    @RequestMapping(value = "/clients/{id}")
    public Optional<Client> getClientById(@PathVariable int id) {

        return clientService.getClient(id);
    }


    @PostMapping("/client/{address_id}")
    public Optional<Client> createClient(@PathVariable("addressid") int address_id,
                                         @RequestBody Client client)
    {
        Address address = clientService.getAddress(address_id);

        client.setAddress_id(address);

        clientService.addClient(client);

        return getClientById(client.getClient_id());
    }


    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public void updateClient(@RequestBody Client Client,@PathVariable int id ) {
        clientService.updateClient(id, Client);
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);

    }
}

