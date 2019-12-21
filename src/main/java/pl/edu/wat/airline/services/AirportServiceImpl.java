package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.airline.dtos.AirportDto;
import pl.edu.wat.airline.entities.AirportEntity;
import pl.edu.wat.airline.repositories.AirportsRepository;
import pl.edu.wat.airline.services.interfaces.AirportService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportsRepository airportsRepository;

    @Autowired
    public AirportServiceImpl(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    public Iterable<AirportDto> findAll() {
        List<AirportDto> airportDtos = new ArrayList<>();

        airportsRepository.findAll()
                .forEach(u -> airportDtos.add(new AirportDto(u.getIata(), u.getName())));

        return airportDtos;
    }

    public AirportDto findByIata(String iata) {
        AirportEntity airportEntity = airportsRepository.findByIata(iata);

        if(airportEntity == null){
            return null;
        }

        return new AirportDto(airportEntity.getIata(), airportEntity.getName());
    }

    public AirportDto addAirport(AirportDto airportDto) {
        if(airportsRepository.findByIata(airportDto.getIata()) != null){
            return null;
        }

        AirportEntity airportEntity = new AirportEntity(airportDto.getIata(), airportDto.getName());

        AirportEntity savedAirportEntity = airportsRepository.save(airportEntity);

        return new AirportDto(savedAirportEntity.getIata(), savedAirportEntity.getName());
    }

    public AirportDto updateAirportName(AirportDto airportDto){
        Long airportEntityId = airportsRepository.findByIata(airportDto.getIata()).getId();

        AirportEntity updatedAirportEntity = airportsRepository.findById(airportEntityId).map(airportEntity -> {
            airportEntity.setName(airportDto.getName());
            return airportsRepository.save(airportEntity);
        }).orElseThrow(() -> new RuntimeException("AirportId " + airportEntityId + " not found."));

        return new AirportDto(updatedAirportEntity.getIata(), updatedAirportEntity.getName());
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//        save(new Airport("WAW","Lotnisko Chopina w Warszawie"));
//        save(new Airport("TXL","Port lotniczy Berlin-Tegel"));
//        save(new Airport("BCN","Port lotniczy Barcelona El Prat"));
//        save(new Airport("VLC", "Port lotniczy Walencja"));
//        save(new Airport("LHR","Port lotniczy Londyn-Heathrow"));
//    }
}
