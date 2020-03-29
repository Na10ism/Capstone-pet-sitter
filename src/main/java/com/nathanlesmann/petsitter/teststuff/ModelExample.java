package com.nathanlesmann.petsitter.teststuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelExample {

    @RequestMapping(value="/")
    public String hello(){
        return "works";
    }
}
