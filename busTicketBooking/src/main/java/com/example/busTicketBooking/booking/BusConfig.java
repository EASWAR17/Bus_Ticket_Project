package com.example.busTicketBooking.booking;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Configuration
public class BusConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BusRepository repository){
        return args -> {
            Bus b1 = new Bus(

                    101,
                    "Chennai",
                    "Trichy",
                    LocalDate.of(2024, Month.SEPTEMBER,1),
                    LocalTime.of(10,00,00),
                    "ac",
                    List.of("l1","l2"),
                    List.of("l3","l4")
            );

            Bus b2 = new Bus(

                    102,
                    "Chennai",
                    "Banglore",
                    LocalDate.of(2024, Month.SEPTEMBER,2),
                    LocalTime.of(12,00,00),
                    "ac",
                    List.of("l1","l2"),
                    List.of("l3","l4")
            );

            Bus b3 = new Bus(

                    103,
                    "Banglore",
                    "Vellore",
                    LocalDate.of(2024, Month.SEPTEMBER,3),
                    LocalTime.of(10,00,00),
                    "non-ac",
                    List.of("l1","l2"),
                    List.of("l3","l4")
            );

            Bus b4 = new Bus(

                    104,
                    "Vellore",
                    "Chennai",
                    LocalDate.of(2024, Month.SEPTEMBER,2),
                    LocalTime.of(11,00,00),
                    "non-ac",
                    List.of("l1","l2"),
                    List.of("l3","l4")
            );

            List<Bus> buses = List.of(b1,b2,b3,b4);

            repository.saveAll(buses);


        };
    }
}