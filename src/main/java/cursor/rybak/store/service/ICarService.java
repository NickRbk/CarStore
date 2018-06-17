package cursor.rybak.store.service;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.web.dto.CarDTO;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

public interface ICarService {

    Stream<Car> getAllAsStream();

    Stream<Car> getAllCarsBySellerIdAsStream(Long sellerId);

    Car add(Long sellerId, CarDTO carDTO);

    Car update(Long sellerId, Long carId, Car carReq);

    ResponseEntity<?> delete(Long sellerId, Long carId);
}
