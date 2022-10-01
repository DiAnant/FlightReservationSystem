package com.dianant.projectdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dianant.projectdemo.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    
}
