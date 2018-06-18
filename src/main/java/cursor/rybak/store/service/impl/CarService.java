package cursor.rybak.store.service.impl;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.repository.CarRepository;
import cursor.rybak.store.domain.repository.SellerRepository;
import cursor.rybak.store.exception.NotFoundException;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.web.dto.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CarService implements ICarService {

    private CarRepository carRepository;
    private SellerRepository sellerRepository;

    @Override
    public Car getCar(Long carId, Long sellerId) {
        return carRepository.getCarByIdAndSellerId(carId, sellerId);
    }

    @Override
    public Stream<Car> getAllAsStream() {
        return carRepository.getAll();
    }

    @Override
    public Stream<Car> getAllCarsBySellerIdAsStream(Long sellerId) {
        return carRepository.findBySellerId(sellerId);
    }

    @Override
    public Car add(Long sellerId, CarDTO carDTO) {
        return sellerRepository
                .findById(sellerId)
                .map(seller -> carRepository.save(Car.builder()
                                .price(carDTO.getPrice())
                                .year(carDTO.getYear())
                                .countryOfRegistration(carDTO.getCountryOfRegistration())
                                .type(carDTO.getType())
                                .model(carDTO.getModel())
                                .mark(carDTO.getMark())
                                .description(carDTO.getDescription())
                                .seller(seller)
                                .build()
                        )
                )
                .orElseThrow(() -> new NotFoundException("SellerId " + sellerId + " not found!"));
    }

    @Override
    public ResponseEntity<?> delete(Long sellerId, Long carId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new NotFoundException("SellerId " + carId + " not found!");
        }

        return carRepository.findById(carId)
                .map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok()
                            .body("CarId " + carId + " deleted successfully");
                })
                .orElseThrow(() -> new NotFoundException("CarId " + carId + "not found"));
    }

    @Override
    public Car update(Long sellerId, Long carId, Car carReq) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new NotFoundException("SellerId " + carId + " not found!");
        }

        return carRepository
                .findById(carId)
                .map(carRepository::save)
                .orElseThrow(() -> new NotFoundException("CarId " + carId + "not found"));
    }
}
