package com.example.busTicketBooking.booking;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }



    public List<Bus> getBuses(){
        return busRepository.findAll();
    }

    public void addNewBus(Bus bus){
        //Optional<Object> busOptional = Optional.ofNullable(busRepository.findBybusnum(bus.getBusnum()));
        Optional<Bus> busOptional =  busRepository
                .findBybusnum(bus.getBusnum());
        if(busOptional.isPresent()){
            throw new IllegalStateException("bus already exist");
        }
        else{
            busRepository.save(bus);
        }
    }

    public void deleteBus(Long busId){
        boolean exist = busRepository.existsById(busId);
        if(!exist){
            throw new IllegalStateException("bus doesnt exist");
        }
        busRepository.deleteById(busId);
    }

    @Transactional
    public void updateBus(Long busId, String source, String destination) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new IllegalStateException(
                        "bus with id " + busId + " doesn't exist"));

        if (source != null && source.length() > 0 && !Objects.equals(bus.getSource(), source)) {
            bus.setSource(source);
        }


        if (destination != null && destination.length() > 0 && !Objects.equals(bus.getDestination(), destination)) {
            bus.setDestination(destination);
        }
        // busRepository.save(bus);




    }

    public List<Bus> getBusesBySourceDestinationAndDate(String source, String destination, LocalDate date) {
        return busRepository.findBySourceAndDestinationAndDate(source, destination, date);
    }

    public List<String> getAvailableSeats(int busnum) {
        Optional<Bus> busOptional = busRepository.findBybusnum(busnum);
        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();
            List<String> availableSeats = new ArrayList<>();
            availableSeats.addAll(bus.getSleeperseats());
            availableSeats.addAll(bus.getSemisleeperseats());
            return availableSeats;
        } else {
            throw new IllegalArgumentException("Bus with busnum " + busnum + " not found.");
        }
    }

    public String bookSeat(int busnum, List<String> seatNumbers) {
        Optional<Bus> busOptional = busRepository.findBybusnum(busnum);
        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();

            // Determine price1 based on actype
            int price1 = bus.getActype().equalsIgnoreCase("ac") ? 20 : 10;

            // Calculate total price for selected seats
            int totalPrice = 0;
            for (String seatNumber : seatNumbers) {
                int price2 = 0;
                if (seatNumber.equalsIgnoreCase("l1") || seatNumber.equalsIgnoreCase("l2")) {
                    price2 = 80;
                } else if (seatNumber.equalsIgnoreCase("l3") || seatNumber.equalsIgnoreCase("l4")) {
                    price2 = 50;
                }
                totalPrice += (price1 + price2);

                // Book the seat (adjust your logic based on your entity structure)
                if (bus.getSleeperseats().contains(seatNumber)) {
                    bus.getSleeperseats().remove(seatNumber);
                } else if (bus.getSemisleeperseats().contains(seatNumber)) {
                    bus.getSemisleeperseats().remove(seatNumber);
                } else {
                    throw new IllegalArgumentException("Seats are already booked");
                }
            }

            busRepository.save(bus);

            // Return confirmation message with total price
            return "Seats " + seatNumbers.toString() + " booked successfully. Total Price: " + totalPrice;
        } else {
            throw new IllegalArgumentException("Bus with busnum " + busnum + " not found.");
        }
    }

    public String restoreSeats(int busnum) {
        Optional<Bus> busOptional = busRepository.findBybusnum(busnum);
        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();

            // Restore all seats to the original state
            bus.setSleeperseats(Arrays.asList("l1", "l2"));
            bus.setSemisleeperseats(Arrays.asList("l3", "l4"));

            busRepository.save(bus);

            return "Seats restored successfully for bus " + busnum;
        } else {
            throw new IllegalArgumentException("Bus with busnum " + busnum + " not found.");
        }
    }


}