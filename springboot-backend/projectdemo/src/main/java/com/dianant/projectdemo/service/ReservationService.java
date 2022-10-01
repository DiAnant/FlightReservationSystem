package com.dianant.projectdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dianant.projectdemo.model.Customer;
import com.dianant.projectdemo.model.Flight;
import com.dianant.projectdemo.model.Reservation;
import com.dianant.projectdemo.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    FlightService flightService;
    @Autowired
    CustomerService customerService;


    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    public ResponseEntity<String> createReservation(Reservation reservation) {
        Customer customer = customerService.getCustomerById(reservation.getCustomerId()).getBody();
        Flight flight = flightService.getFlightById(reservation.getFlightId()).getBody();

        if(flight.getAvailableSeats() <= 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "All seats of this flight are booked. Please find a different flight!");
        }

        reservation.setSeatNumber(flight.getAvailableSeats());
        flightService.bookASeat(reservation.getFlightId());
        Reservation savedReservation = reservationRepository.save(reservation);


        return ResponseEntity.ok("Thanks " + customer.getFirstName() + " for using our services. Your Flight is successfully booked. Your reservation id is " + savedReservation.getId());
    }

    public ResponseEntity<Reservation> getReservationsByCustomerId(Long id) {
        Reservation reservation = reservationRepository.findById(id).
        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Reservation found by reservation id: " + id));
        return ResponseEntity.ok(reservation);
    }

    public ResponseEntity<String> deleteReservation(Long id) {
        if(!reservationRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Reservation found by reservation id: " + id);
        }
        reservationRepository.deleteById(id);
        return ResponseEntity.ok("Reservation deleted successfully!");
    }
    
}
