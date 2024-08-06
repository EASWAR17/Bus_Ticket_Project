package com.example.busTicketBooking.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {


    Optional<Bus> findBybusnum(Integer busnum);
    List<Bus> findBySourceAndDestinationAndDate(String source, String destination, LocalDate date);
}