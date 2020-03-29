package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {


    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/addresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }


    @RequestMapping(value = "/addresses/{id}")
    public Optional<Address> getAddress(@PathVariable int id) {
        return addressService.getAddress(id);
    }


    @RequestMapping(value = "/addresses", method = RequestMethod.POST)
    public void addAddress(@RequestBody Address Address) {
        addressService.addAddress(Address);
    }

    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.PUT)
    public void updateAddress(@RequestBody Address Address, @PathVariable int id) {
        addressService.updateAddress(id, Address);
    }

    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.DELETE)
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);

    }
}
