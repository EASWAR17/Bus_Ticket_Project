package com.example.busTicketBooking.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bus")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public List<Bus> getBuses(){
        return busService.getBuses();
    }

    @GetMapping(path = "/search")
    public List<Bus> getBusesBySourceDestinationAndDate(@RequestParam("source") String source,
                                                        @RequestParam("destination") String destination,
                                                        @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return busService.getBusesBySourceDestinationAndDate(source, destination, date);
    }



    @PostMapping
    public void registerNewBus(@RequestBody Bus bus){
        busService.addNewBus(bus);
    }

    @DeleteMapping(path = "{busId}")
    public void deleteBus(@PathVariable("busId") Long busId){
        busService.deleteBus(busId);
    }

    @PutMapping(path = "{busId}")
    public void updateBus(
            @PathVariable("busId") Long busId,
            @RequestParam(required = false)String source,
            @RequestParam(required = false)String destination){
        busService.updateBus(busId,source,destination);
    }

    @PostMapping("/{busnum}/book")
    public String bookSeat(
            @PathVariable int busnum,
            @RequestBody List<String> seatNumbers) {
        try {
            return busService.bookSeat(busnum, seatNumbers);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/{busnum}/available-seats")
    public List<String> getAvailableSeats(@PathVariable int busnum) {
        return busService.getAvailableSeats(busnum);
    }

    @PostMapping("/{busnum}/restore-seats")
    public String restoreSeats(@PathVariable int busnum) {
        try {
            return busService.restoreSeats(busnum);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }


}