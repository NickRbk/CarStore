package cursor.rybak.store.domain.repository;

import cursor.rybak.store.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM cars", nativeQuery = true)
    Stream<Car> getAll();

    Stream<Car> findBySellerId(Long sellerId);

    Car getCarByIdAndSellerId(Long carId, Long sellerId);
}
