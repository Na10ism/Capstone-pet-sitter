package com.nathanlesmann.petsitter.services;
import com.nathanlesmann.petsitter.entities.Appointment;
import com.nathanlesmann.petsitter.entities.Pet;
import com.nathanlesmann.petsitter.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    public AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments = new ArrayList<>();

        appointmentRepository.findAll()
                .forEach(appointments::add);

        return appointments;
    }

    public Optional<Appointment> getAppointmentById(int appointment_id){
        return appointmentRepository.findById(appointment_id);
    }

    public void updateOrAddAppointment(Appointment theAppointment) {
        appointmentRepository.save(theAppointment);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAllAppointmentsByClientId(int client_id) {
        return appointmentRepository.getAllAppointmentsByClientId(client_id);
    }
}