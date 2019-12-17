package pl.edu.wat.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.entity.SeatPrice;
import pl.edu.wat.airline.repository.SeatPriceRepo;

import java.util.Optional;

@Service
public class SeatPriceService {

    private SeatPriceRepo seatPriceRepo;

    @Autowired
    public SeatPriceService(SeatPriceRepo seatPriceRepo) {
        this.seatPriceRepo = seatPriceRepo;
    }

    public Optional<SeatPrice> findById(Long id) {
        return seatPriceRepo.findById(id);
    }

    public Iterable<SeatPrice> findAll() {
        return seatPriceRepo.findAll();
    }

    public SeatPrice save(SeatPrice seatPrice) {
        return seatPriceRepo.save(seatPrice);
    }

    public void deleteById(Long id) {
        seatPriceRepo.deleteById(id);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//        save(new SeatPrice(100.00,200.00,300.00,30.00,20.00,0.00,50.00,100.00,150.00));
//        save(new SeatPrice(200.00,300.00,400.00,40.00,30.00,20.00,100.00,150.00,200.00));
//        save(new SeatPrice(2.50,2.30,6.50,66.50,80.50,66.00,55.00,221.00,1.00));
//        save(new SeatPrice(2.50,2.80,6.50,66.50,80.50,66.00,55.00,221.00,1.00));
//    }
}
