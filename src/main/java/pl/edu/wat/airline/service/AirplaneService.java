package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.Airplane;
import pl.edu.wat.airline.repository.AirplaneRepo;

import java.util.Optional;

@Service
public class AirplaneService {

    private AirplaneRepo airplaneRepo;

    @Autowired
    public AirplaneService(AirplaneRepo airplaneRepo) {
        this.airplaneRepo = airplaneRepo;
    }

    public Optional<Airplane> findById(Long id) {
        return airplaneRepo.findById(id);
    }

    public Iterable<Airplane> findAll() {
        return airplaneRepo.findAll();
    }

    public Airplane save(Airplane airplane) {
        return airplaneRepo.save(airplane);
    }

    public void deleteById(Long id) {
        airplaneRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
//        save(new Airplane("Airbus A320",100,40,40));
//        save(new Airplane("Airbus A380",120,30,70));
//        save(new Airplane("Airbus A310",100,10,10));
//        save(new Airplane("Airbus A318",160,25,25));
    }
}
