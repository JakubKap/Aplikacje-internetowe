package pl.edu.wat.airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wat.airline.dtos.*;
import pl.edu.wat.airline.entities.*;
import pl.edu.wat.airline.repositories.*;
import pl.edu.wat.airline.services.interfaces.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private FlightsRepository flightsRepository;
    private UsersRepository usersRepository;
    private AirportsRepository airportsRepository;
    private AirplanesRepository airplanesRepository;
    private SeatPricesRepository seatPricesRepository;
    private EmailService email;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  FlightsRepository flightsRepository,
                                  UsersRepository usersRepository,
                                  AirportsRepository airportsRepository,
                                  AirplanesRepository airplanesRepository,
                                  SeatPricesRepository seatPricesRepository,
                                  EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.flightsRepository = flightsRepository;
        this.usersRepository = usersRepository;
        this.airportsRepository = airportsRepository;
        this.airplanesRepository = airplanesRepository;
        this.seatPricesRepository = seatPricesRepository;
        this.email = emailService;
    }

//    public Optional<ReservationEntity> findById(Long id) {
//        return reservationRepository.findById(id);
//    }

    public Iterable<ReservationDto> findAll() {
        List<ReservationDto> reservationDtos = new ArrayList<>();

        reservationRepository.findAll()
                .forEach(a ->
                        reservationDtos.add(new ReservationDto(
                                a.getReservationNo(),
                                a.getIsReservationPaid(),
                                a.getIsOnlineCheckInMade(),
                                a.getNumOfAdults(),
                                a.getNumOfInfants(),
                                a.getNumOfChildren(),
                                a.getTravelClass(),
                                a.getReservationPrice(),
                                getFlightDtoFromReservationEntity(a),
                                getUserDtoFromReservationEntity(a)
                        )));
        return reservationDtos;
    }


    public Iterable<ReservationDto> findByUserLogin(String userLogin) {
        List<ReservationDto> reservationDtos = new ArrayList<>();

        reservationRepository.findByUserLogin(userLogin).forEach(
                r -> reservationDtos.add(new ReservationDto(r.getReservationNo(),
                        r.getIsReservationPaid(),
                        r.getIsOnlineCheckInMade(),
                        r.getNumOfAdults(),
                        r.getNumOfInfants(),
                        r.getNumOfChildren(),
                        r.getTravelClass(),
                        r.getReservationPrice(),
                        getFlightDtoFromReservationEntity(r),
                        getUserDtoFromReservationEntity(r)))
        );

        if(reservationDtos.size() == 0){
            return null;
        }

        return reservationDtos;
    }

    public ReservationDto findByReservationNo(String reservationNo) {
        ReservationEntity reservationEntity = reservationRepository.findByReservationNo(reservationNo);

        if(reservationEntity == null) {
            return null;
        }
        return new ReservationDto(
                reservationEntity.getReservationNo(),
                reservationEntity.getIsReservationPaid(),
                reservationEntity.getIsOnlineCheckInMade(),
                reservationEntity.getNumOfAdults(),
                reservationEntity.getNumOfInfants(),
                reservationEntity.getNumOfChildren(),
                reservationEntity.getTravelClass(),
                reservationEntity.getReservationPrice(),
                getFlightDtoFromReservationEntity(reservationEntity),
                getUserDtoFromReservationEntity(reservationEntity)
        );
    }

//    public Iterable<ReservationEntity> findAll() {
//        return reservationRepository.findAll();
//    }

//    public ReservationEntity save(ReservationEntity reservationEntity) {
//        return reservationRepository.save(reservationEntity);
//    }

    public ReservationDto addReservation(ReservationDto reservationDto) {
        ReservationEntity foundReservationEntity = reservationRepository.findByReservationNo(reservationDto.getReservationNo());

        if(foundReservationEntity != null) {
            return null;
        }

        AirportDto depAirport = reservationDto.getFlightDto().getDepartureAirport();
        AirportEntity depAirportEntity = airportsRepository.findByIata(depAirport.getIata());

        AirportDto arrAirport = reservationDto.getFlightDto().getArrivalAirport();
        AirportEntity arrAirportEntity = airportsRepository.findByIata(arrAirport.getIata());

        AirplaneDto airplane = reservationDto.getFlightDto().getAirplane();
        AirplaneEntity airplaneEntity = airplanesRepository.findByName(airplane.getName());

        SeatPriceDto seatPrice = reservationDto.getFlightDto().getSeatPrice();
        SeatPriceEntity seatPriceEntity = seatPricesRepository.findSeatPriceEntity(
                seatPrice.getEconomicClassAdultPrice(),
                seatPrice.getBusinessClassAdultPrice(),
                seatPrice.getFirstClassAdultPrice(),
                seatPrice.getEconomicClassInfantPrice(),
                seatPrice.getBusinessClassInfantPrice(),
                seatPrice.getFirstClassInfantPrice(),
                seatPrice.getEconomicClassChildPrice(),
                seatPrice.getBusinessClassChildPrice(),
                seatPrice.getFirstClassChildPrice());

        FlightEntity flightEntity = new FlightEntity(
                reservationDto.getFlightDto().getFlightNumber(),
                reservationDto.getFlightDto().getDepartureDateTime(),
                reservationDto.getFlightDto().getArrivalDateTime(),
                reservationDto.getFlightDto().getBoardingDateTime(),
                reservationDto.getFlightDto().getGateNumber(),
                reservationDto.getFlightDto().getStatus(),
                depAirportEntity,
                arrAirportEntity,
                airplaneEntity,
                seatPriceEntity);

        UserEntity userEntity = new UserEntity(
                reservationDto.getUserDto().getName(),
                reservationDto.getUserDto().getSurname(),
                reservationDto.getUserDto().getBirthdate(),
                reservationDto.getUserDto().getPhoneNumber(),
                reservationDto.getUserDto().getEmail(),
                reservationDto.getUserDto().getLogin(),
                reservationDto.getUserDto().getPassword());

        ReservationEntity reservationEntity = new ReservationEntity(
                reservationDto.getReservationNo(),
                reservationDto.getIsReservationPaid(),
                reservationDto.getIsOnlineCheckInMade(),
                reservationDto.getNumOfAdults(),
                reservationDto.getNumOfInfants(),
                reservationDto.getNumOfChildren(),
                reservationDto.getTravelClass(),
                reservationDto.getReservationPrice(),
                flightEntity,
                userEntity);

        ReservationEntity savedReservationEntity = reservationRepository.save(reservationEntity);

        return new ReservationDto(
                savedReservationEntity.getReservationNo(),
                savedReservationEntity.getIsReservationPaid(),
                savedReservationEntity.getIsOnlineCheckInMade(),
                savedReservationEntity.getNumOfAdults(),
                savedReservationEntity.getNumOfInfants(),
                savedReservationEntity.getNumOfChildren(),
                savedReservationEntity.getTravelClass(),
                savedReservationEntity.getReservationPrice(),
                getFlightDtoFromReservationEntity(savedReservationEntity),
                getUserDtoFromReservationEntity(savedReservationEntity));
    }

    public ReservationDto updateReservation(ReservationDto reservationDto) {
        Long reservationEntityId = reservationRepository.findByReservationNo(reservationDto.getReservationNo()).getId();

        ReservationEntity updatedReservationEntity = reservationRepository.findById(reservationEntityId).map(reservationEntity -> {
            reservationEntity.setReservationNo(reservationDto.getReservationNo());
            reservationEntity.setIsReservationPaid(reservationDto.getIsReservationPaid());
            reservationEntity.setIsOnlineCheckInMade(reservationDto.getIsOnlineCheckInMade());
            reservationEntity.setNumOfAdults(reservationDto.getNumOfAdults());
            reservationEntity.setNumOfInfants(reservationDto.getNumOfInfants());
            reservationEntity.setNumOfChildren(reservationDto.getNumOfChildren());
            reservationEntity.setTravelClass(reservationDto.getTravelClass());
            reservationEntity.setReservationPrice(reservationDto.getReservationPrice());

            FlightDto flightDto = reservationDto.getFlightDto();
            FlightEntity flightEntity = flightsRepository.findByFlightNumber(flightDto.getFlightNumber());
            reservationEntity.setFlightEntity(flightEntity);

            UserDto userDto = reservationDto.getUserDto();
            UserEntity userEntity = usersRepository.findByLogin(userDto.getLogin());
            reservationEntity.setUserEntity(userEntity);

            return reservationRepository.save(reservationEntity);
        }).orElseThrow(() -> new RuntimeException("ReservationId " + reservationEntityId + " not found."));

        return new ReservationDto(
                updatedReservationEntity.getReservationNo(),
                updatedReservationEntity.getIsReservationPaid(),
                updatedReservationEntity.getIsOnlineCheckInMade(),
                updatedReservationEntity.getNumOfAdults(),
                updatedReservationEntity.getNumOfInfants(),
                updatedReservationEntity.getNumOfChildren(),
                updatedReservationEntity.getTravelClass(),
                updatedReservationEntity.getReservationPrice(),
                getFlightDtoFromReservationEntity(updatedReservationEntity),
                getUserDtoFromReservationEntity(updatedReservationEntity));
    }

//    public void deleteById(Long id) {
//        reservationRepository.deleteById(id);
//    }

    public ReservationDto updatePaidStatus(@RequestParam String reservationNo) {
        Long reservationEntityId = reservationRepository.findByReservationNo(reservationNo).getId();

        ReservationEntity updatedReservationEntity = reservationRepository.findById(reservationEntityId).map(reservationEntity -> {

            reservationEntity.setIsReservationPaid(true);

            return reservationRepository.save(reservationEntity);
        }).orElseThrow(() -> new RuntimeException("Cannot pay for reservationId " + reservationEntityId + "."));

        return new ReservationDto(
                updatedReservationEntity.getReservationNo(),
                updatedReservationEntity.getIsReservationPaid(),
                updatedReservationEntity.getIsOnlineCheckInMade(),
                updatedReservationEntity.getNumOfAdults(),
                updatedReservationEntity.getNumOfInfants(),
                updatedReservationEntity.getNumOfChildren(),
                updatedReservationEntity.getTravelClass(),
                updatedReservationEntity.getReservationPrice(),
                getFlightDtoFromReservationEntity(updatedReservationEntity),
                getUserDtoFromReservationEntity(updatedReservationEntity));
    }

    public ReservationDto deleteReservation(@RequestParam String reservationNo) {
        Long reservationEntityId = reservationRepository.findByReservationNo(reservationNo).getId();

        ReservationEntity deleteReservationEntity = reservationRepository.findById(reservationEntityId).map(r -> {

            reservationRepository.delete(r);

            return r;
        }).orElseThrow(() -> new RuntimeException("Reservation " + reservationNo + " cannot be deleted."));
        return new ReservationDto(
                deleteReservationEntity.getReservationNo(),
                deleteReservationEntity.getIsReservationPaid(),
                deleteReservationEntity.getIsOnlineCheckInMade(),
                deleteReservationEntity.getNumOfAdults(),
                deleteReservationEntity.getNumOfInfants(),
                deleteReservationEntity.getNumOfChildren(),
                deleteReservationEntity.getTravelClass(),
                deleteReservationEntity.getReservationPrice(),
                getFlightDtoFromReservationEntity(deleteReservationEntity),
                getUserDtoFromReservationEntity(deleteReservationEntity));
    }

    public ReservationDto deleteUserReservation(@RequestBody ReservationDto reservationDto) {
        Long reservationEntityId = reservationRepository.findByReservationNo(reservationDto.getReservationNo()).getId();

        ReservationEntity deleteReservationEntity = reservationRepository.findById(reservationEntityId).map(r -> {
            try {
                email.sendEmail(reservationDto.getUserDto().getEmail(), "AirportApp reservation No. " + reservationDto.getReservationNo(), "Faithfully AirportApp team");
            } catch (MailAuthenticationException e) {
                System.out.println("Wrong user email address");
            }
            reservationRepository.delete(r);
            return r;
        }).orElseThrow(() -> new RuntimeException("Email cannot be send"));
        return new ReservationDto(
                deleteReservationEntity.getReservationNo(),
                deleteReservationEntity.getIsReservationPaid(),
                deleteReservationEntity.getIsOnlineCheckInMade(),
                deleteReservationEntity.getNumOfAdults(),
                deleteReservationEntity.getNumOfInfants(),
                deleteReservationEntity.getNumOfChildren(),
                deleteReservationEntity.getTravelClass(),
                deleteReservationEntity.getReservationPrice(),
                getFlightDtoFromReservationEntity(deleteReservationEntity),
                getUserDtoFromReservationEntity(deleteReservationEntity));
    }

    private FlightDto getFlightDtoFromReservationEntity(ReservationEntity reservationEntity) {
        FlightEntity flightEntity = reservationEntity.getFlightEntity();

        AirportDto departureAirportDto = new AirportDto(
                flightEntity.getDepartureAirportEntity().getIata(),
                flightEntity.getDepartureAirportEntity().getName());

        AirportDto arrivalAirportDto = new AirportDto(
                flightEntity.getArrivalAirportEntity().getIata(),
                flightEntity.getArrivalAirportEntity().getName());

        AirplaneDto airplaneDto = new AirplaneDto(
                flightEntity.getAirplaneEntity().getName(),
                flightEntity.getAirplaneEntity().getEconomicSeatsAmount(),
                flightEntity.getAirplaneEntity().getBusinessSeatsAmount(),
                flightEntity.getAirplaneEntity().getFirstClassSeatsAmount());

        SeatPriceDto seatPriceDto = new SeatPriceDto(
                flightEntity.getSeatPriceEntity().getEconomicClassAdultPrice(),
                flightEntity.getSeatPriceEntity().getBusinessClassAdultPrice(),
                flightEntity.getSeatPriceEntity().getFirstClassAdultPrice(),
                flightEntity.getSeatPriceEntity().getEconomicClassInfantPrice(),
                flightEntity.getSeatPriceEntity().getBusinessClassInfantPrice(),
                flightEntity.getSeatPriceEntity().getFirstClassInfantPrice(),
                flightEntity.getSeatPriceEntity().getEconomicClassChildPrice(),
                flightEntity.getSeatPriceEntity().getBusinessClassChildPrice(),
                flightEntity.getSeatPriceEntity().getFirstClassChildPrice());

        return new FlightDto(
                flightEntity.getFlightNumber(),
                flightEntity.getDepartureDateTime(),
                flightEntity.getArrivalDateTime(),
                flightEntity.getBoardingDateTime(),
                flightEntity.getGateNumber(),
                flightEntity.getStatus(),
                departureAirportDto,
                arrivalAirportDto,
                airplaneDto,
                seatPriceDto);
    }

    private UserDto getUserDtoFromReservationEntity(ReservationEntity reservationEntity) {
        UserEntity userEntity = reservationEntity.getUserEntity();
        return new UserDto(
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getBirthdate(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail(),
                userEntity.getLogin(),
                userEntity.getPassword()
                );
    }
}
