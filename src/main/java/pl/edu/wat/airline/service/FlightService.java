package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.Flight;
import pl.edu.wat.airline.repository.FlightRepo;

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

    public Flight save(Flight flight) {
        return flightRepo.save(flight);
    }

    public void deleteById(Long id) {
        flightRepo.deleteById(id);
    }
}
