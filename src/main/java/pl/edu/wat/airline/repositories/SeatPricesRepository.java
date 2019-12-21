package pl.edu.wat.airline.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.wat.airline.entities.SeatPriceEntity;


public interface SeatPricesRepository extends CrudRepository<SeatPriceEntity, Long> {

    @Query (
            "SELECT new pl.edu.wat.airline.entities.SeatPriceEntity(s.id, s.economicClassAdultPrice, s.businessClassAdultPrice, s.firstClassAdultPrice, "
                    + "s.economicClassInfantPrice, s.businessClassInfantPrice, s.firstClassInfantPrice, "
                    + "s.economicClassChildPrice, s.businessClassChildPrice, s.firstClassChildPrice)"
            + "FROM SeatPriceEntity s "
            + "WHERE s.economicClassAdultPrice = :economicClassAdultPrice AND s.businessClassAdultPrice = :businessClassAdultPrice AND s.firstClassAdultPrice = :firstClassAdultPrice "
            + "AND s.economicClassInfantPrice = :economicClassInfantPrice AND s.businessClassInfantPrice = :businessClassInfantPrice AND s.firstClassInfantPrice = :firstClassInfantPrice "
            + "AND s.economicClassChildPrice = :economicClassChildPrice AND s.businessClassChildPrice = :businessClassChildPrice AND s.firstClassChildPrice = :firstClassChildPrice"
    )
    SeatPriceEntity findSeatPriceEntity(@Param("economicClassAdultPrice") Double economicClassAdultPrice, @Param("businessClassAdultPrice") Double businessClassAdultPrice, @Param("firstClassAdultPrice") Double firstClassAdultPrice,
                                    @Param("economicClassInfantPrice") Double economicClassInfantPrice, @Param("businessClassInfantPrice") Double businessClassInfantPrice, @Param("firstClassInfantPrice") Double firstClassInfantPrice,
                                    @Param("economicClassChildPrice") Double economicClassChildPrice, @Param("businessClassChildPrice") Double businessClassChildPrice, @Param("firstClassChildPrice") Double firstClassChildPrice);
}
