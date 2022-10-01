package com.dianant.projectdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dianant.projectdemo.model.Flight;
import com.dianant.projectdemo.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightRepository.findAll());
    }

    public ResponseEntity<String> createFlight(Flight flight) {

        // we need to make sure that flightBoarding time is not of past
        flightRepository.save(flight);
        return ResponseEntity.ok("Flight Record Saved Successfully");
    }

    public ResponseEntity<Flight> getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No customer with id: " + id + " exists in the databse!"));
        return ResponseEntity.ok(flight);
    }

    public ResponseEntity<String> deleteFlight(Long id) {
        
        if(! flightRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No flight with id: " + id + " found in the database.");
        }

        flightRepository.deleteById(id);
        return ResponseEntity.ok("Flight Record Successfully deleted from the database!");
    }

    // this method will only be called if flightId exists so no need to check for it
    public void bookASeat(Long flightId) {
        Flight flight = flightRepository.getById(flightId);
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);
    }   
}
