package cursor.rybak.store.service.car.impl;

import cursor.rybak.store.exception.NotFoundException;
import cursor.rybak.store.model.Car;
import cursor.rybak.store.repository.car.CarRepository;
import cursor.rybak.store.repository.seller.SellerRepository;
import cursor.rybak.store.service.car.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private SellerRepository sellerRepository;

    @Override
    public Page<Car> getAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Page<Car> getAllCarsBySellerId(Long sellerId, Pageable pageable) {
        return carRepository.findBySellerId(sellerId, pageable);
    }

    @Override
    public Car add(Long sellerId, Car car) {
        return sellerRepository
                .findById(sellerId)
                .map(seller -> {
                    car.setSeller(seller);
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new NotFoundException("SellerId " + sellerId + " not found!"));
    }

    @Override
    public Car update(Long sellerId, Long carId, Car carReq) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new NotFoundException("SellerId " + carId + " not found!");
        }

        return carRepository
                .findById(carId)
                .map(car -> {
                    car.setDescription(carReq.getDescription());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new NotFoundException("CarId " + carId + "not found"));
    }

    @Override
    public ResponseEntity<?> delete(Long sellerId, Long carId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new NotFoundException("SellerId " + carId + " not found!");
        }

        return carRepository.findById(carId)
                .map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new NotFoundException("CarId " + carId + "not found"));
    }
}
