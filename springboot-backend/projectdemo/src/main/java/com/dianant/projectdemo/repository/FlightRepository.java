package com.dianant.projectdemo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dianant.projectdemo.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{
    
}
