package com.nathanlesmann.petsitter.repositories;

import com.nathanlesmann.petsitter.entities.Appointment;
import com.nathanlesmann.petsitter.entities.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    @Query("select s from Appointment s where client_id= :client_id ")
    public List<Appointment> getAllAppointmentsByClientId(int client_id);
}
