package com.nathanlesmann.petsitter.teststuff;

import com.nathanlesmann.petsitter.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ModelExample {

    @RequestMapping(value="/")
    public String hello(){

        int one = 1;

        return "clients/${one}";
    }




}
