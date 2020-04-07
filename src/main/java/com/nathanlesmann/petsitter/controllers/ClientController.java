package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PetService petService;

    @RequestMapping(value = "/clients")
    public String getAllClients(Model model)
    {
        List<Client> theClients = clientService.getAllClients();

        model.addAttribute("clients", theClients);

        return "clients/AllClients";
    }

    @RequestMapping(value="/clients/showFormForAdd")
    public String showFormForAdd(Model model){

        // create model attribute to bind the form data
        Client client = new Client();

        model.addAttribute("client", client);

        return "clients/clientForm";
    }


    @RequestMapping(value = "/api/clients/{id}")
    public Optional<Client> getClientById(@PathVariable int id) {

        return clientService.getClient(id);
    }

    @RequestMapping(value = "/clients/{id}")
    public String getClientById(@PathVariable int id, Model model) {

//        Optional<Client> theClient= clientService.getClient(id);

        Client client = clientService.getClient(id).orElse(null);

        model.addAttribute("client", client);

        model.addAttribute("address", clientService.getAddress(id));


        assert client != null;
        //create getAllPetsByClientId
        model.addAttribute("pets", petService.getAllPetsByClientId(client.getClient_id()));


        return "clients/singleClient";
    }


    @PostMapping("/client/{address_id}")
    public Optional<Client> createClient(@PathVariable("addressid") int address_id,
                                         @RequestBody Client client)
    {
        Address address = clientService.getAddress(address_id);

        client.setAddress_id(address);

//        petService.getAllPets().forEach(client.setPets(););

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

    @PostMapping("/clients/save")
    public String saveClient(@ModelAttribute("client") Client theClient) {

        // save the employee
        clientService.saveClient(theClient);

        // use a redirect to prevent duplicate submissions
        return "redirect:/address/showFormForAddress";
    }
}

