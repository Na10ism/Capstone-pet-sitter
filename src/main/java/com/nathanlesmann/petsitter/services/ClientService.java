package com.nathanlesmann.petsitter.services;

import com.nathanlesmann.petsitter.entities.Address;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    public ClientRepository clientRepository;

//    @Autowired
//    public AddressRepository addressRepository;


    public List<Client> getAllClients() {

        List<Client> clients = new ArrayList<>();

        clientRepository.findAll()
                .forEach(clients::add);

        return clients;
    }
//
//    @Query("Select s from pet where client_id= :client_id")
//    public List<Pet> getAllPetsByClientId(Client client_id){
//
//        List<Pet> pets = new ArrayList<>();
//
//
//
//    }
//    public List<Pet> getAllPets

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public Address getAddress(int id) {
        return clientRepository.getAddress(id);
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.findById(id);
    }

    public void updateClient(int id, Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}
