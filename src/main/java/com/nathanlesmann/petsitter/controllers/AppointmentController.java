package com.nathanlesmann.petsitter.controllers;

import com.nathanlesmann.petsitter.entities.Appointment;
import com.nathanlesmann.petsitter.entities.Client;
import com.nathanlesmann.petsitter.entities.Pet;
import com.nathanlesmann.petsitter.services.AppointmentService;
import com.nathanlesmann.petsitter.services.ClientService;
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

    @RequestMapping(value="/appointments")
    public String getAllAppointments(Model model){
        List<Appointment> appointments = appointmentService.getAllAppointments();

        model.addAttribute("appointments", appointments);

        return "appointments/allAppointments";
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
