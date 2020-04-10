package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.AddressService;
import com.nathanlesmann.petsitter.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AddressController {


    @Autowired
    private AddressService addressService;

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/address")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }


    @RequestMapping(value = "/address/{id}")
    public Optional<Address> getAddress(@PathVariable int id) {
        return addressService.getAddress(id);
    }


    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public void addAddress(@RequestBody Address Address) {
        addressService.saveAddress(Address);
    }



    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);

    }

    @RequestMapping(value="/address/showFormForAddress")
    public String showFormForAddress(Model model,
                                     @ModelAttribute("client") Client theClient){

        // create model attribute to bind the form data
        Address address = new Address();


//        theClient.setAddress_id(address);
        clientService.addClient(theClient);

        model.addAttribute("address", address);
        model.addAttribute("client", theClient);

        return "address/addressForm";
    }

    @PostMapping("/address/save/{client_id}")
    public String saveAddress(@PathVariable int client_id,
                              @ModelAttribute("address") Address theAddress) {


        addressService.saveAddress(theAddress);

        Address address = clientService.getAddress(theAddress.getAddress_id());

        Client client = clientService.getClientById(client_id).orElse(null);

        assert client != null;
        client.setAddress_id(address);

        clientService.updateClientById(client.getClient_id(),client);

        // use a redirect to prevent duplicate submissions
        return "redirect:/clients/" + client_id;
    }
}
