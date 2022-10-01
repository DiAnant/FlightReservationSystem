package com.dianant.projectdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dianant.projectdemo.model.Flight;
import com.dianant.projectdemo.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {   

    @Autowired
    FlightService flightService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return flightService.getAllFlights();
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> createFlight(@Valid @RequestBody Flight flight){
        return flightService.createFlight(flight);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        return flightService.getFlightById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id){
        return flightService.deleteFlight(id);
    }

}
