package com.moviebooking.theaterservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatres")
public class TheaterController {

    @GetMapping
    public ResponseEntity<String> getTheaterServiceResponse(){
        return  ResponseEntity.ok("Theater Service is Working :))");
    }

}
