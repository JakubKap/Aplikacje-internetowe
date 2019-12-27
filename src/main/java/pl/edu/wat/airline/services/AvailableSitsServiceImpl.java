package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.dtos.*;
import pl.edu.wat.airline.entities.*;
import pl.edu.wat.airline.repositories.*;
import pl.edu.wat.airline.services.interfaces.AvailableSitsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvailableSitsServiceImpl implements AvailableSitsService {

    private AvailableSitsRepository availableSitsRepository;

    @Autowired
    public AvailableSitsServiceImpl(
            AvailableSitsRepository availableSitsRepository
    ) {
        this.availableSitsRepository = availableSitsRepository;
    }

    public Iterable<AvailableSitsDto> findAll() {
        List<AvailableSitsDto> availableSitsDtos = new ArrayList<>();

        availableSitsRepository.findAll()
                .forEach(a ->
                        availableSitsDtos.add(new AvailableSitsDto(
                                a.getAirplaneName(),
                                a.getSeatClassCode(),
                                a.getDepartureCode(),
                                a.getArrivalCode(),
                                a.getFlightNumber(),
                                a.getGateNumber(),
                                a.getDepartureDateTime(),
                                a.getArrivalDateTime(),
                                a.getBoardingDateTime(),
                                a.getDepartureAirport(),
                                a.getArrivalAirport(),
                                a.getEkoAvailable(),
                                a.getBusAvailable(),
                                a.getPierAvailable(),
                                a.getBusinessClassAdultPrice(),
                                a.getBusinessClassChildPrice(),
                                a.getBusinessClassInfantPrice(),
                                a.getEconomicClassAdultPrice(),
                                a.getEconomicClassChildPrice(),
                                a.getEconomicClassInfantPrice(),
                                a.getFirstClassAdultPrice(),
                                a.getFirstClassChildPrice(),
                                a.getFirstClassInfantPrice(),
                                a.getPrice()
                                )));
        return availableSitsDtos;
    }

    public Iterable<AvailableSitsDto> findAvailableBusSits(
            String departureDateTime,
            String departureAirport,
            String arrivalAirport,
            Integer classSeatsNum
    ) {
        List<AvailableSitsDto> availableSitsDtos = new ArrayList<>();
        Iterable<AvailableSitsEntity> availableSitsEntities;

        availableSitsEntities = availableSitsRepository.findAvailableBusSits(departureDateTime,departureAirport, arrivalAirport, classSeatsNum);

        if(availableSitsEntities == null) {
            return null;
        }

        availableSitsEntities.forEach(a -> availableSitsDtos.add(new AvailableSitsDto(
                a.getAirplaneName(),
                a.getSeatClassCode(),
                a.getDepartureCode(),
                a.getArrivalCode(),
                a.getFlightNumber(),
                a.getGateNumber(),
                a.getDepartureDateTime(),
                a.getArrivalDateTime(),
                a.getBoardingDateTime(),
                a.getDepartureAirport(),
                a.getArrivalAirport(),
                a.getEkoAvailable(),
                a.getBusAvailable(),
                a.getPierAvailable(),
                a.getBusinessClassAdultPrice(),
                a.getBusinessClassChildPrice(),
                a.getBusinessClassInfantPrice(),
                a.getEconomicClassAdultPrice(),
                a.getEconomicClassChildPrice(),
                a.getEconomicClassInfantPrice(),
                a.getFirstClassAdultPrice(),
                a.getFirstClassChildPrice(),
                a.getFirstClassInfantPrice(),
                a.getPrice()
        )));
        return availableSitsDtos;
    }

public Iterable<AvailableSitsDto> findAvailableEkoSits(
        String departureDateTime,
        String departureAirport,
        String arrivalAirport,
        Integer classSeatsNum
) {
    List<AvailableSitsDto> availableSitsDtos = new ArrayList<>();
    Iterable<AvailableSitsEntity> availableSitsEntities;

    availableSitsEntities = availableSitsRepository.findAvailableEkoSits(departureDateTime,departureAirport, arrivalAirport, classSeatsNum);

    if(availableSitsEntities == null) {
        return null;
    }

    availableSitsEntities.forEach(a -> availableSitsDtos.add(new AvailableSitsDto(
            a.getAirplaneName(),
            a.getSeatClassCode(),
            a.getDepartureCode(),
            a.getArrivalCode(),
            a.getFlightNumber(),
            a.getGateNumber(),
            a.getDepartureDateTime(),
            a.getArrivalDateTime(),
            a.getBoardingDateTime(),
            a.getDepartureAirport(),
            a.getArrivalAirport(),
            a.getEkoAvailable(),
            a.getBusAvailable(),
            a.getPierAvailable(),
            a.getBusinessClassAdultPrice(),
            a.getBusinessClassChildPrice(),
            a.getBusinessClassInfantPrice(),
            a.getEconomicClassAdultPrice(),
            a.getEconomicClassChildPrice(),
            a.getEconomicClassInfantPrice(),
            a.getFirstClassAdultPrice(),
            a.getFirstClassChildPrice(),
            a.getFirstClassInfantPrice(),
            a.getPrice()
    )));
    return availableSitsDtos;
}

    public Iterable<AvailableSitsDto> findAvailablePierSits(
            String departureDateTime,
            String departureAirport,
            String arrivalAirport,
            Integer classSeatsNum
    ) {
        List<AvailableSitsDto> availableSitsDtos = new ArrayList<>();
        Iterable<AvailableSitsEntity> availableSitsEntities;

        availableSitsEntities = availableSitsRepository.findAvailablePierSits(departureDateTime,departureAirport, arrivalAirport, classSeatsNum);

        if(availableSitsEntities == null) {
            return null;
        }

        availableSitsEntities.forEach(a -> availableSitsDtos.add(new AvailableSitsDto(
                a.getAirplaneName(),
                a.getSeatClassCode(),
                a.getDepartureCode(),
                a.getArrivalCode(),
                a.getFlightNumber(),
                a.getGateNumber(),
                a.getDepartureDateTime(),
                a.getArrivalDateTime(),
                a.getBoardingDateTime(),
                a.getDepartureAirport(),
                a.getArrivalAirport(),
                a.getEkoAvailable(),
                a.getBusAvailable(),
                a.getPierAvailable(),
                a.getBusinessClassAdultPrice(),
                a.getBusinessClassChildPrice(),
                a.getBusinessClassInfantPrice(),
                a.getEconomicClassAdultPrice(),
                a.getEconomicClassChildPrice(),
                a.getEconomicClassInfantPrice(),
                a.getFirstClassAdultPrice(),
                a.getFirstClassChildPrice(),
                a.getFirstClassInfantPrice(),
                a.getPrice()
        )));
        return availableSitsDtos;
    }

//    private AirportDto getDepartureAirportDtoFromAvailableSitsEntity(AvailableSitsEntity availableSitsEntity) {
//        AirportEntity departureEntity = availableSitsEntity.getDepartureEntity();
//        return new AirportDto(departureEntity.getIata(), departureEntity.getName());
//    }
//
//    private AirportDto getArrivalAirportDtoFromAvailableSitsEntity(AvailableSitsEntity availableSitsEntity) {
//        AirportEntity arrivalEntity = availableSitsEntity.getArrivalEntity();
//        return new AirportDto(arrivalEntity.getIata(), arrivalEntity.getName());
//    }
//
//    private AirplaneDto getAirplaneDtoFromAvailableSitsEntity(AvailableSitsEntity availableSitsEntity) {
//        AirplaneEntity airplaneEntity = availableSitsEntity.getAirplaneEntity();
//        return new AirplaneDto(
//                airplaneEntity.getName(),
//                airplaneEntity.getEconomicSeatsAmount(),
//                airplaneEntity.getBusinessSeatsAmount(),
//                airplaneEntity.getFirstClassSeatsAmount());
//    }
//
//    private SeatPriceDto getSeatPriceDtoFromAvailableSitsEntity(AvailableSitsEntity availableSitsEntity) {
//        SeatPriceEntity seatPriceEntity = availableSitsEntity.getSeatClassEntity();
//        return new SeatPriceDto(
//                seatPriceEntity.getEconomicClassAdultPrice(),
//                seatPriceEntity.getBusinessClassAdultPrice(),
//                seatPriceEntity.getFirstClassAdultPrice(),
//                seatPriceEntity.getEconomicClassInfantPrice(),
//                seatPriceEntity.getBusinessClassInfantPrice(),
//                seatPriceEntity.getFirstClassInfantPrice(),
//                seatPriceEntity.getEconomicClassChildPrice(),
//                seatPriceEntity.getBusinessClassChildPrice(),
//                seatPriceEntity.getFirstClassChildPrice());
//    }
//
//    private FlightDto getFlightDtoFromAvailableSitsEntity(AvailableSitsEntity availableSitsEntity) {
//
//        FlightEntity flightEntity = availableSitsEntity.getFlightEntity();
//
//        AirportDto departureAirportDto = new AirportDto(
//                flightEntity.getDepartureAirportEntity().getIata(),
//                flightEntity.getDepartureAirportEntity().getName());
//
//        AirportDto arrivalAirportDto = new AirportDto(
//                flightEntity.getArrivalAirportEntity().getIata(),
//                flightEntity.getArrivalAirportEntity().getName());
//
//        AirplaneDto airplaneDto = new AirplaneDto(
//                flightEntity.getAirplaneEntity().getName(),
//                flightEntity.getAirplaneEntity().getEconomicSeatsAmount(),
//                flightEntity.getAirplaneEntity().getBusinessSeatsAmount(),
//                flightEntity.getAirplaneEntity().getFirstClassSeatsAmount());
//
//        SeatPriceDto seatPriceDto = new SeatPriceDto(
//                flightEntity.getSeatPriceEntity().getEconomicClassAdultPrice(),
//                flightEntity.getSeatPriceEntity().getBusinessClassAdultPrice(),
//                flightEntity.getSeatPriceEntity().getFirstClassAdultPrice(),
//                flightEntity.getSeatPriceEntity().getEconomicClassInfantPrice(),
//                flightEntity.getSeatPriceEntity().getBusinessClassInfantPrice(),
//                flightEntity.getSeatPriceEntity().getFirstClassInfantPrice(),
//                flightEntity.getSeatPriceEntity().getEconomicClassChildPrice(),
//                flightEntity.getSeatPriceEntity().getBusinessClassChildPrice(),
//                flightEntity.getSeatPriceEntity().getFirstClassChildPrice());
//
//        return new FlightDto(
//                flightEntity.getFlightNumber(),
//                flightEntity.getDepartureDateTime(),
//                flightEntity.getArrivalDateTime(),
//                flightEntity.getBoardingDateTime(),
//                flightEntity.getGateNumber(),
//                flightEntity.getStatus(),
//                departureAirportDto,
//                arrivalAirportDto,
//                airplaneDto,
//                seatPriceDto);
//    }
}
