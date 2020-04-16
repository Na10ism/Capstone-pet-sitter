package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Appointment;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.entities.Pet;
import com.nathanlesmann.petsitter.services.AppointmentService;
import com.nathanlesmann.petsitter.services.ClientService;
import com.nathanlesmann.petsitter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PetService petService;

    @RequestMapping(value="/appointments")
    public String getAllAppointments(Model model){
        List<Appointment> appointments = appointmentService.getAllAppointments();

        model.addAttribute("appointments", appointments);

        return "appointments/allAppointments";
    }

    @RequestMapping(value = "/appointments/{appointment_id}")
    public String getClientById(@PathVariable int appointment_id, Model model) {


        Appointment appointment =
                appointmentService.getAppointmentById(appointment_id).orElse(null);

        assert appointment != null;
        Client client = appointment.getClient();

        model.addAttribute("appointment",appointment);
        model.addAttribute("client", client);

        assert client != null;
        model.addAttribute("address", clientService.getAddressById(client.getIdFromAddress_id()));

        List<Pet> pets = petService.getAllPetsByClientId(client.getClient_id());
        model.addAttribute("pets", pets);

//        Pet pet = petService.getPet()

        for (Pet pet: pets) {
            System.out.println(pet);
        }

        return "appointments/singleAppointment";
    }

    @RequestMapping(value = "/appointments/showFormForAppointment/{client_id}")
    public String showFormForAppointment(Model model, @PathVariable int client_id) {

        // create model attribute to bind the form data
        Appointment appointment = new Appointment();

        Client client = clientService.getClientById(client_id).orElse(null);

        model.addAttribute("appointment", appointment);
        model.addAttribute("client", client);

        return "appointments/appointmentForm";

    }

    @PostMapping("/appointments/save/{client_id}")
    public String saveAppointment(@PathVariable int client_id,
                                  @ModelAttribute("appointment") Appointment theAppointment) {

        // save the employee
        Client client = clientService.getClientById(client_id).orElse(null);


        theAppointment.setClient(client);
        appointmentService.updateOrAddAppointment(theAppointment);

        return "redirect:/clients/" + client_id;
    }

}
