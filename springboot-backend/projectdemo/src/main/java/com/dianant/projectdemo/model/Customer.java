package com.dianant.projectdemo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "First name cannot be blank!")
    private String firstName;
    private String lastName;

    @NotBlank(message = "Phone Number is required!")
    @Column(length = 10, unique = true)
    private String phoneNumber;

    @NotBlank(message = "Email Address is required!")
    @Email
    @Column(unique = true)
    private String emailId;

    private LocalDate customerCreationDate;

    public Customer(){

    }

    //  constructor does not allow id to initiated
    public Customer(String firstName, String lastName, String phoneNumber, String emailId,
        LocalDate customerCreationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.customerCreationDate = customerCreationDate;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getCustomerCreationDate() {
        return customerCreationDate;
    }

    public void setCustomerCreationDate(LocalDate customerCreationDate) {
        this.customerCreationDate = customerCreationDate;
    }

    

}
