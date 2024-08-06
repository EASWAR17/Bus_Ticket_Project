package com.example.busTicketBooking.booking;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class Bus {

    @Id
    private Integer busnum; // Primary key
    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private String actype;
    private List<String> sleeperseats;
    private List<String> semisleeperseats;

    public Bus() {
    }

    public Bus(Integer busnum, String source, String destination, LocalDate date, LocalTime time, String actype, List<String> sleeperseats, List<String> semisleeperseats) {
        this.busnum = busnum;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.actype = actype;
        this.sleeperseats = sleeperseats;
        this.semisleeperseats = semisleeperseats;
    }

    public List<String> getSemisleeperseats() {
        return semisleeperseats;
    }

    public void setSemisleeperseats(List<String> semisleeperseats) {
        this.semisleeperseats = semisleeperseats;
    }

    public List<String> getSleeperseats() {
        return sleeperseats;
    }

    public void setSleeperseats(List<String> sleeperseats) {
        this.sleeperseats = sleeperseats;
    }

    public String getActype() {
        return actype;
    }

    public void setActype(String actype) {
        this.actype = actype;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getBusnum() {
        return busnum;
    }

    public void setBusnum(Integer busnum) {
        this.busnum = busnum;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busnum=" + busnum +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", actype='" + actype + '\'' +
                ", sleeperseats=" + sleeperseats +
                ", semisleeperseats=" + semisleeperseats +
                '}';
    }



}