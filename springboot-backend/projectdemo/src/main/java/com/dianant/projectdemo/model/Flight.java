package com.dianant.projectdemo.model;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Airline Name cannot be blank!")
    private String airlineName;

    @NotBlank(message = "From-Location cannot be blank!")
    private String fromLocation;

    @NotBlank(message = "To-Location cannot be blank!")
    private String toLocation;

    @NotBlank(message = "Airport Gate Number cannot be blank")
    private String gateNumber;

    // need to create a custom validator for checking that time/date/zone is correctly formatted. Also need to make sure that date/time is not past and has at least 4 hour difference from current time.
    // @NotBlank(message = "Boarding time cannot be blank")
    private ZonedDateTime dateTimeZone;

    private static final int INITIAL_SEAT_CAPACITY = 100;
    private int availableSeats;

    

    public Flight(Long id, String airlineName, String fromLocation, String toLocation,
            String gateNumber, ZonedDateTime dateTimeZone,int availableSeats) {

        this.id = id;
        this.airlineName = airlineName;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.gateNumber = gateNumber;
        this.dateTimeZone = dateTimeZone;
        this.availableSeats = INITIAL_SEAT_CAPACITY;
    }

    public Flight(){
        this.availableSeats = INITIAL_SEAT_CAPACITY;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAirlineName() {
        return airlineName;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public String getFromLocation() {
        return fromLocation;
    }
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }
    public String getToLocation() {
        return toLocation;
    }
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }
    public String getGateNumber() {
        return gateNumber;
    }
    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }
    public ZonedDateTime getDateTimeZone() {
        return dateTimeZone;
    }
    public void setDateTimeZone(ZonedDateTime dateTimeZone) {
        this.dateTimeZone = dateTimeZone;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    
}
