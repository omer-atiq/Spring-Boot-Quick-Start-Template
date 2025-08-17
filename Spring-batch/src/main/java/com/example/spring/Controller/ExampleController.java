package com.example.spring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/hello")
    public String getName() {
        return "This is just the beginning, Let take this to next heights";
    }


    @GetMapping("/example")
    public String getUserName() {
        return "I am robot do you want me to say anything else?";
    }


}
