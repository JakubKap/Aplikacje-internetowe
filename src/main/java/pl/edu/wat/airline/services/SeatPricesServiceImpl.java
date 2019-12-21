package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.dtos.SeatPriceDto;
import pl.edu.wat.airline.entities.SeatPriceEntity;
import pl.edu.wat.airline.repositories.SeatPricesRepository;
import pl.edu.wat.airline.services.interfaces.SeatPricesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatPricesServiceImpl implements SeatPricesService {

    private SeatPricesRepository seatPricesRepository;

    @Autowired
    public SeatPricesServiceImpl(SeatPricesRepository seatPricesRepository) {
        this.seatPricesRepository = seatPricesRepository;
    }

    public Iterable<SeatPriceDto> findAll() {
        List<SeatPriceDto> seatPriceDtos = new ArrayList<>();

        seatPricesRepository.findAll()
                .forEach(u -> seatPriceDtos.add(new SeatPriceDto(u.getEconomicClassAdultPrice(), u.getBusinessClassAdultPrice(), u.getFirstClassAdultPrice(),
                        u.getEconomicClassInfantPrice(), u.getBusinessClassInfantPrice(), u.getFirstClassInfantPrice(),
                        u.getEconomicClassChildPrice(), u.getBusinessClassChildPrice(), u.getFirstClassChildPrice())));

        return seatPriceDtos;
    }

    public SeatPriceDto addSeatPrice(SeatPriceDto seatPriceDto) {
        SeatPriceEntity seatPriceEntity = new SeatPriceEntity(seatPriceDto.getEconomicClassAdultPrice(), seatPriceDto.getBusinessClassAdultPrice(), seatPriceDto.getFirstClassAdultPrice(),
                seatPriceDto.getEconomicClassInfantPrice(), seatPriceDto.getBusinessClassInfantPrice(), seatPriceDto.getFirstClassInfantPrice(),
                seatPriceDto.getEconomicClassChildPrice(), seatPriceDto.getBusinessClassChildPrice(), seatPriceDto.getFirstClassChildPrice());

        SeatPriceEntity savedSeatPriceEntity = seatPricesRepository.save(seatPriceEntity);

        return new SeatPriceDto(savedSeatPriceEntity.getEconomicClassAdultPrice(), savedSeatPriceEntity.getBusinessClassAdultPrice(), savedSeatPriceEntity.getFirstClassAdultPrice(),
                savedSeatPriceEntity.getEconomicClassInfantPrice(), savedSeatPriceEntity.getBusinessClassInfantPrice(), savedSeatPriceEntity.getFirstClassInfantPrice(),
                savedSeatPriceEntity.getEconomicClassChildPrice(), savedSeatPriceEntity.getBusinessClassChildPrice(), savedSeatPriceEntity.getFirstClassChildPrice());
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//        save(new SeatPrice(100.00,200.00,300.00,30.00,20.00,0.00,50.00,100.00,150.00));
//        save(new SeatPrice(200.00,300.00,400.00,40.00,30.00,20.00,100.00,150.00,200.00));
//        save(new SeatPrice(2.50,2.30,6.50,66.50,80.50,66.00,55.00,221.00,1.00));
//        save(new SeatPrice(2.50,2.80,6.50,66.50,80.50,66.00,55.00,221.00,1.00));
//    }
}
