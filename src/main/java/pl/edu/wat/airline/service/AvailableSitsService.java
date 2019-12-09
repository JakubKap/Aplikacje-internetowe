package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.AvailableSits;
import pl.edu.wat.airline.repository.AvailableSitsRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AvailableSitsService {

    private AvailableSitsRepo availableSitsRepo;

    @Autowired
    public AvailableSitsService(AvailableSitsRepo availableSitsRepo) {
        this.availableSitsRepo = availableSitsRepo;
    }

    public Optional<AvailableSits> findById(Long id) {
        return availableSitsRepo.findById(id);
    }

    public Iterable<AvailableSits> findAll() {
        return availableSitsRepo.findAll();
    }

    public AvailableSits findByDepartureDateTimeAndArrivalDateTimeAndDepartureAirportAndArrivalAirportAndEkoAvailableIsGreaterThanEqualAndBusAvailableIsGreaterThanEqualAndPierAvailableIsGreaterThanEqual(
            LocalDateTime departureDateTime,
            LocalDateTime arrivalDateTime,
            String departureAirport,
            String arrivalAirport,
            Integer eko,
            Integer bus,
            Integer pier
    ) {
        return availableSitsRepo.findByDepartureDateTimeAndArrivalDateTimeAndDepartureAirportAndArrivalAirportAndEkoAvailableIsGreaterThanEqualAndBusAvailableIsGreaterThanEqualAndPierAvailableIsGreaterThanEqual(
                departureDateTime, arrivalDateTime, departureAirport, arrivalAirport, eko, bus, pier
        );
    }

    public AvailableSits save(AvailableSits as) {
        return availableSitsRepo.save(as);
    }

    public void deleteById(Long id) {
        availableSitsRepo.deleteById(id);
    }
}
