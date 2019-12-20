package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entities.Flight;
import pl.edu.wat.airline.repositories.FlightRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FlightService {

    private FlightRepo flightRepo;

    @Autowired
    public FlightService(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    public Optional<Flight> findById(Long id) {
        return flightRepo.findById(id);
    }

    public Iterable<Flight> findAll() {
        return flightRepo.findAll();
    }

    public Flight findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(String flightNumber, LocalDateTime departureDatetime){
        return flightRepo.findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(flightNumber, departureDatetime);
    }

    public Flight save(Flight flight) {
        return flightRepo.save(flight);
    }

    public void deleteById(Long id) {
        flightRepo.deleteById(id);
    }
}
