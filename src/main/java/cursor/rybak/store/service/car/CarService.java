package cursor.rybak.store.service.car;

import cursor.rybak.store.domain.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CarService {
    Page<Car> getAll(Pageable pageable);

    Page<Car> getAllCarsBySellerId(Long sellerId, Pageable pageable);

    Car add(Long sellerId, Car car);

    Car update(Long sellerId, Long carId, Car carReq);

    ResponseEntity<?> delete(Long sellerId, Long carId);
}
