package com.nathanlesmann.petsitter.services;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    public AddressRepository addressRepository;

    public List<Address> getAllAddresses() {

        List<Address> Addresses = new ArrayList<>();

        addressRepository.findAll()
                .forEach(Addresses::add);

        return Addresses;
    }

    public Optional<Address> getAddress(int id) {
        return addressRepository.findById(id);
    }

    public void updateAddress(int id, Address Address) {
        addressRepository.save(Address);
    }

    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }

    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

}
