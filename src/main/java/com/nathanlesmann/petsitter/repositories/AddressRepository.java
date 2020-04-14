package com.nathanlesmann.petsitter.repositories;

import com.nathanlesmann.petsitter.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
