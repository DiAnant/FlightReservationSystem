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

import com.dianant.projectdemo.model.Reservation;
import com.dianant.projectdemo.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Reservation>> getAllFlights(){
        return reservationService.getAllReservations();
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> createFlight(@Valid @RequestBody Reservation reservation){
        return reservationService.createReservation(reservation);
    }
    
    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<Reservation> getReservationsByCustomerId(@PathVariable Long id){
        return reservationService.getReservationsByCustomerId(id);
    }

    // delete by using reservationId
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id){
        return reservationService.deleteReservation(id);
    }

}
