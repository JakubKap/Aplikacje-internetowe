package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wat.airline.dtos.AirplaneDto;
import pl.edu.wat.airline.dtos.AirportDto;
import pl.edu.wat.airline.dtos.FlightDto;
import pl.edu.wat.airline.dtos.SeatPriceDto;
import pl.edu.wat.airline.entities.AirplaneEntity;
import pl.edu.wat.airline.entities.AirportEntity;
import pl.edu.wat.airline.entities.FlightEntity;
import pl.edu.wat.airline.entities.SeatPriceEntity;
import pl.edu.wat.airline.repositories.AirplanesRepository;
import pl.edu.wat.airline.repositories.AirportsRepository;
import pl.edu.wat.airline.repositories.FlightsRepository;
import pl.edu.wat.airline.repositories.SeatPricesRepository;
import pl.edu.wat.airline.services.interfaces.FlightsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightsServiceImpl implements FlightsService {

    private FlightsRepository flightsRepository;
    private AirportsRepository airportsRepository;
    private AirplanesRepository airplanesRepository;
    private SeatPricesRepository seatPricesRepository;

    @Autowired
    public FlightsServiceImpl(FlightsRepository flightsRepository, AirportsRepository airportsRepository,
                              AirplanesRepository airplanesRepository, SeatPricesRepository seatPricesRepository) {
        this.flightsRepository = flightsRepository;
        this.airportsRepository = airportsRepository;
        this.airplanesRepository = airplanesRepository;
        this.seatPricesRepository = seatPricesRepository;
    }

    public Iterable<FlightDto> findAll() {
    List<FlightDto> flightDtos = new ArrayList<>();

    flightsRepository.findAll()
            .forEach(u ->
                        flightDtos.add(new FlightDto(u.getFlightNumber(), u.getDepartureDateTime(), u.getArrivalDateTime(),
                                u.getBoardingDateTime(), u.getGateNumber(), u.getStatus(),
                                getDepAirportDtoFromFlightEntity(u), getArrAirportDtoFromFlightEntity(u), getAirplaneDtoFromFlightEntity(u),
                                getSeatPriceDtoFromFlightEntity(u))));

        return flightDtos;
    }

    public FlightDto findByFlightNumber(String flightNumber) {
        FlightEntity flightEntity = flightsRepository.findByFlightNumber(flightNumber);

        if(flightEntity == null){
            return null;
        }

        return new FlightDto(flightEntity.getFlightNumber(), flightEntity.getDepartureDateTime(), flightEntity.getArrivalDateTime(),
                flightEntity.getBoardingDateTime(), flightEntity.getGateNumber(), flightEntity.getStatus(),
                getDepAirportDtoFromFlightEntity(flightEntity), getArrAirportDtoFromFlightEntity(flightEntity), getAirplaneDtoFromFlightEntity(flightEntity),
                getSeatPriceDtoFromFlightEntity(flightEntity));
    }

    public FlightDto findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(String flightNumber, LocalDateTime departureDatetime){
        FlightEntity flightEntity = flightsRepository.findByFlightNumberAndDepartureDateTimeIsGreaterThanEqual(flightNumber, departureDatetime);

        if(flightEntity == null){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return new FlightDto(flightEntity.getFlightNumber(), flightEntity.getDepartureDateTime(), flightEntity.getArrivalDateTime(),
                flightEntity.getBoardingDateTime(), flightEntity.getGateNumber(), flightEntity.getStatus(),
                getDepAirportDtoFromFlightEntity(flightEntity), getArrAirportDtoFromFlightEntity(flightEntity), getAirplaneDtoFromFlightEntity(flightEntity),
                getSeatPriceDtoFromFlightEntity(flightEntity));
    }

    public FlightDto addFlight(FlightDto flightDto) {
        FlightEntity foundFlightEntity = flightsRepository.findByFlightNumber(flightDto.getFlightNumber());

        if(foundFlightEntity != null){
            return null;
        }

        AirportDto depAirport = flightDto.getDepartureAirport();
        AirportEntity depAirportEntity = airportsRepository.findByIata(depAirport.getIata());

        AirportDto arrAirport = flightDto.getArrivalAirport();
        AirportEntity arrAirportEntity = airportsRepository.findByIata(arrAirport.getIata());

        AirplaneDto airplane = flightDto.getAirplane();
        AirplaneEntity airplaneEntity = airplanesRepository.findByName(airplane.getName());

        SeatPriceDto seatPrice = flightDto.getSeatPrice();
        SeatPriceEntity seatPriceEntity = seatPricesRepository.findSeatPriceEntity(seatPrice.getEconomicClassAdultPrice(), seatPrice.getBusinessClassAdultPrice(), seatPrice.getFirstClassAdultPrice(),
                seatPrice.getEconomicClassInfantPrice(), seatPrice.getBusinessClassInfantPrice(), seatPrice.getFirstClassInfantPrice(),
                seatPrice.getEconomicClassChildPrice(), seatPrice.getBusinessClassChildPrice(), seatPrice.getFirstClassChildPrice());

        FlightEntity flightEntity = new FlightEntity(flightDto.getFlightNumber(), flightDto.getDepartureDateTime(), flightDto.getArrivalDateTime(),
                flightDto.getBoardingDateTime(), flightDto.getGateNumber(), flightDto.getStatus(),
                depAirportEntity, arrAirportEntity, airplaneEntity, seatPriceEntity);

        FlightEntity savedFlightEntity = flightsRepository.save(flightEntity);

        return new FlightDto(savedFlightEntity.getFlightNumber(), savedFlightEntity.getDepartureDateTime(), savedFlightEntity.getArrivalDateTime(),
                savedFlightEntity.getBoardingDateTime(), savedFlightEntity.getGateNumber(), savedFlightEntity.getStatus(),
                getDepAirportDtoFromFlightEntity(savedFlightEntity), getArrAirportDtoFromFlightEntity(savedFlightEntity), getAirplaneDtoFromFlightEntity(savedFlightEntity),
                getSeatPriceDtoFromFlightEntity(savedFlightEntity));
    }

    public FlightDto updateFlight(FlightDto flightDto){
        Long flightEntityId = flightsRepository.findByFlightNumber(flightDto.getFlightNumber()).getId();

         FlightEntity updatedFlightEntity = flightsRepository.findById(flightEntityId).map(flightEntity -> {
            flightEntity.setFlightNumber(flightDto.getFlightNumber());
            flightEntity.setDepartureDateTime(flightDto.getDepartureDateTime());
            flightEntity.setArrivalDateTime(flightDto.getArrivalDateTime());
            flightEntity.setBoardingDateTime(flightDto.getBoardingDateTime());
            flightEntity.setGateNumber(flightDto.getGateNumber());
            flightEntity.setStatus(flightDto.getStatus());

            AirportDto depAirport = flightDto.getDepartureAirport();
            AirportEntity depAirportEntity = airportsRepository.findByIata(depAirport.getIata());
            flightEntity.setDepartureAirportEntity(depAirportEntity);

            AirportDto arrAirport = flightDto.getArrivalAirport();
            AirportEntity arrAirportEntity = airportsRepository.findByIata(arrAirport.getIata());
            flightEntity.setArrivalAirportEntity(arrAirportEntity);

            AirplaneDto airplane = flightDto.getAirplane();
            AirplaneEntity airplaneEntity = airplanesRepository.findByName(airplane.getName());
            flightEntity.setAirplaneEntity(airplaneEntity);

             SeatPriceDto seatPrice = flightDto.getSeatPrice();
             SeatPriceEntity seatPriceEntity = seatPricesRepository.findSeatPriceEntity(seatPrice.getEconomicClassAdultPrice(), seatPrice.getBusinessClassAdultPrice(), seatPrice.getFirstClassAdultPrice(),
                     seatPrice.getEconomicClassInfantPrice(), seatPrice.getBusinessClassInfantPrice(), seatPrice.getFirstClassInfantPrice(),
                     seatPrice.getEconomicClassChildPrice(), seatPrice.getBusinessClassChildPrice(), seatPrice.getFirstClassChildPrice());
            flightEntity.setSeatPriceEntity(seatPriceEntity);

            return flightsRepository.save(flightEntity);
        }).orElseThrow(() -> new RuntimeException("FlightId " + flightEntityId + " not found."));

         return  new FlightDto(updatedFlightEntity.getFlightNumber(), updatedFlightEntity.getDepartureDateTime(), updatedFlightEntity.getArrivalDateTime(),
                 updatedFlightEntity.getBoardingDateTime(), updatedFlightEntity.getGateNumber(), updatedFlightEntity.getStatus(),
                 getDepAirportDtoFromFlightEntity(updatedFlightEntity), getArrAirportDtoFromFlightEntity(updatedFlightEntity), getAirplaneDtoFromFlightEntity(updatedFlightEntity),
                 getSeatPriceDtoFromFlightEntity(updatedFlightEntity));
    }

    public Iterable<FlightDto> deleteFlight(@RequestBody FlightDto flightDto) {
        Long flightEntityId = flightsRepository.findByFlightNumber(flightDto.getFlightNumber()).getId();

        List<FlightDto> flightDtos = new ArrayList<>();

        FlightEntity deleteFlightEntity = flightsRepository.findById(flightEntityId).map(f -> {
            flightsRepository.delete(f);
            flightDtos
                    .add(new FlightDto(
                            f.getFlightNumber(),
                            f.getDepartureDateTime(),
                            f.getArrivalDateTime(),
                            f.getBoardingDateTime(),
                            f.getGateNumber(),
                            f.getStatus(),
                            getDepAirportDtoFromFlightEntity(f),
                            getArrAirportDtoFromFlightEntity(f),
                            getAirplaneDtoFromFlightEntity(f),
                            getSeatPriceDtoFromFlightEntity(f)));
            return f;
        }).orElseThrow(() -> new RuntimeException("FlightNumber " + flightDto.getFlightNumber() + " cannot be deleted."));
        return flightDtos;
    }

    private AirportDto getDepAirportDtoFromFlightEntity(FlightEntity flightEntity){
        AirportEntity departureAirportEntity = flightEntity.getDepartureAirportEntity();
        return new AirportDto(departureAirportEntity.getIata(), departureAirportEntity.getName());
    }

    private AirportDto getArrAirportDtoFromFlightEntity(FlightEntity flightEntity){
        AirportEntity arrivalAirportEntity = flightEntity.getArrivalAirportEntity();
        return new AirportDto(arrivalAirportEntity.getIata(), arrivalAirportEntity.getName());
    }

    private AirplaneDto getAirplaneDtoFromFlightEntity(FlightEntity flightEntity){
        AirplaneEntity airplaneEntity = flightEntity.getAirplaneEntity();
        return new AirplaneDto(airplaneEntity.getName(), airplaneEntity.getEconomicSeatsAmount(),
                airplaneEntity.getBusinessSeatsAmount(), airplaneEntity.getFirstClassSeatsAmount());
    }

    private SeatPriceDto getSeatPriceDtoFromFlightEntity(FlightEntity flightEntity){
        SeatPriceEntity seatPriceEntity = flightEntity.getSeatPriceEntity();
        return new SeatPriceDto(seatPriceEntity.getEconomicClassAdultPrice(), seatPriceEntity.getBusinessClassAdultPrice(), seatPriceEntity.getFirstClassAdultPrice(),
                seatPriceEntity.getEconomicClassInfantPrice(), seatPriceEntity.getBusinessClassInfantPrice(), seatPriceEntity.getFirstClassInfantPrice(),
                seatPriceEntity.getEconomicClassChildPrice(), seatPriceEntity.getBusinessClassChildPrice(), seatPriceEntity.getFirstClassChildPrice());
    }
}
