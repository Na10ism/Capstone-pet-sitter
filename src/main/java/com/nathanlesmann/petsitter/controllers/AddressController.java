package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.services.AddressService;
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
        addressService.addAddress(Address);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public void updateAddress(@RequestBody Address Address, @PathVariable int id) {
        addressService.updateAddress(id, Address);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);

    }

    @RequestMapping(value="/address/showFormForAddress")
    public String showFormForAddress(Model model){

        // create model attribute to bind the form data
        Address address = new Address();

        model.addAttribute("address", address);

        return "address/addressForm";
    }

    @PostMapping("/address/save")
    public String saveAddress(@ModelAttribute("address") Address theAddress) {

        // save the employee
        addressService.saveAddress(theAddress);

        // use a redirect to prevent duplicate submissions
        return "redirect:/clients";
    }
}
