package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.Airport;
import pl.edu.wat.airline.repository.AirportRepo;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AirportService {

    private AirportRepo airportRepo;

    @Autowired
    public AirportService(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    public Optional<Airport> findById(Long id) {
        return airportRepo.findById(id);
    }

    public Iterable<Airport> findAll() {
        return airportRepo.findAll();
    }

    public Airport save(Airport airport) {
        return airportRepo.save(airport);
    }

    public void deleteById(Long id) {
        airportRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new Airport("WAW","Lotnisko Chopina w Warszawie"));
        save(new Airport("TXL","Port lotniczy Berlin-Tegel"));
        save(new Airport("BCN","Port lotniczy Barcelona El Prat"));
        save(new Airport("VLC", "Port lotniczy Walencja"));
        save(new Airport("LHR","Port lotniczy Londyn-Heathrow"));
    }
}
