package cursor.rybak.store.service;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.web.dto.CarDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ICarService {

    Optional<Car> getCar(Long carId, Long sellerId);

    Stream<Car> getAllAsStream();

    Stream<Car> getAllSortedByKeyAsStream(String key);

    Stream<Car> getAllCarsBySellerIdAsStream(Long sellerId);

    void add(Long sellerId, List<CarDTO> carDTOs);

    void update(Long sellerId, Long carId, Car car, String email);

    void delete(Long sellerId, Long carId, String email);
}
