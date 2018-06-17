package cursor.rybak.store.service;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.web.dto.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICarService {
    Page<Car> getAll(Pageable pageable);

    Page<Car> getAllCarsBySellerId(Long sellerId, Pageable pageable);

    Car add(Long sellerId, CarDTO carDTO);

    Car update(Long sellerId, Long carId, Car carReq);

    ResponseEntity<?> delete(Long sellerId, Long carId);
}
