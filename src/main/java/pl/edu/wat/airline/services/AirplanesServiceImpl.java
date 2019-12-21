package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.dtos.AirplaneDto;
import pl.edu.wat.airline.entities.AirplaneEntity;
import pl.edu.wat.airline.repositories.AirplanesRepository;
import pl.edu.wat.airline.services.interfaces.AirplanesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirplanesServiceImpl implements AirplanesService {

    private AirplanesRepository airplanesRepository;

    @Autowired
    public AirplanesServiceImpl(AirplanesRepository airplanesRepository) {
        this.airplanesRepository = airplanesRepository;
    }

    public Iterable<AirplaneDto> findAll() {
        List<AirplaneDto> airplaneDtos = new ArrayList<>();

        airplanesRepository.findAll()
                .forEach(u -> airplaneDtos.add(new AirplaneDto(u.getName(), u.getEconomicSeatsAmount(), u.getBusinessSeatsAmount(), u.getFirstClassSeatsAmount())));
        return airplaneDtos;
    }

    public AirplaneDto findByName(String name){
        AirplaneEntity airplaneEntity = airplanesRepository.findByName(name);

        if(airplaneEntity == null){
            return null;
        }

        return new AirplaneDto(airplaneEntity.getName(), airplaneEntity.getEconomicSeatsAmount(),
                airplaneEntity.getBusinessSeatsAmount(), airplaneEntity.getFirstClassSeatsAmount());
    }

    public AirplaneDto addAirplane(AirplaneDto airplaneDto) {
        if(airplanesRepository.findByName(airplaneDto.getName()) != null){
            return null;
        }

        AirplaneEntity savedAirplaneEntity = airplanesRepository.save(new AirplaneEntity(airplaneDto.getName(), airplaneDto.getEconomicSeatsAmount(),
                airplaneDto.getBusinessSeatsAmount(), airplaneDto.getFirstClassSeatsAmount()));

        return new AirplaneDto(savedAirplaneEntity.getName(), savedAirplaneEntity.getEconomicSeatsAmount(),
                savedAirplaneEntity.getBusinessSeatsAmount(), savedAirplaneEntity.getFirstClassSeatsAmount());
    }

    public AirplaneDto updateAirplane(AirplaneDto airplaneDto){
        Long airplaneEntityId = airplanesRepository.findByName(airplaneDto.getName()).getId();

        AirplaneEntity updatedAirplaneEntity = airplanesRepository.findById(airplaneEntityId).map(airplaneEntity -> {
            airplaneEntity.setName(airplaneDto.getName());
            airplaneEntity.setBusinessSeatsAmount(airplaneDto.getBusinessSeatsAmount());
            airplaneEntity.setEconomicSeatsAmount(airplaneDto.getEconomicSeatsAmount());
            airplaneEntity.setFirstClassSeatsAmount(airplaneDto.getFirstClassSeatsAmount());
            return airplanesRepository.save(airplaneEntity);
        }).orElseThrow(() -> new RuntimeException("AirportId " + airplaneEntityId + " not found."));

        return new AirplaneDto(updatedAirplaneEntity.getName(), updatedAirplaneEntity.getEconomicSeatsAmount(),
                updatedAirplaneEntity.getBusinessSeatsAmount(), updatedAirplaneEntity.getFirstClassSeatsAmount());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
//        save(new Airplane("Airbus A320",100,40,40));
//        save(new Airplane("Airbus A380",120,30,70));
//        save(new Airplane("Airbus A310",100,10,10));
//        save(new Airplane("Airbus A318",160,25,25));
    }
}
