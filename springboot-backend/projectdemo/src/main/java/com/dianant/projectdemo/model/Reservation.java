package com.dianant.projectdemo.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // not user provided, will be setup automatically at the time of reservation creation
    private Integer seatNumber;
    
    @NotBlank(message = "Customer-Id cannot be blank")
    private Long customerId;

    @NotBlank(message = "Flight-Id cannot be blank")
    private Long flightId;

    // not user provided, will be setup automatically in constructor
    private ZonedDateTime reservationZonedDateTime;

    public Reservation(){
        this.reservationZonedDateTime = ZonedDateTime.now();
    }
    
    public Reservation(Long id, Integer seatNumber, Long customerId, Long flightId, ZonedDateTime reservaZonedDateTime) {

        this.id = id;
        this.seatNumber = seatNumber;
        this.customerId = customerId;
        this.flightId = flightId;
        this.reservationZonedDateTime = ZonedDateTime.now();
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long passengerId) {
        this.customerId = passengerId;
    }
    public Long getFlightId() {
        return flightId;
    }
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public ZonedDateTime getReservationZonedDateTime() {
        return reservationZonedDateTime;
    }

    public void setReservationZonedDateTime(ZonedDateTime reservationZonedDateTime) {
        this.reservationZonedDateTime = reservationZonedDateTime;
    }    
}
