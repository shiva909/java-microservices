package com.moviebooking.movieservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @GetMapping
    public ResponseEntity<String> getMovieServiceResponse(){
        return ResponseEntity.ok("Movie Service is Working");
    }

}
