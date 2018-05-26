package cursor.rybak.store.controller;

import cursor.rybak.store.exception.ResourceNotFoundException;
import cursor.rybak.store.model.Car;
import cursor.rybak.store.repository.car.CarRepository;
import cursor.rybak.store.repository.seller.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CarController {

    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public CarController(CarRepository carRepository, SellerRepository sellerRepository) {
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @GetMapping("/sellers/{sellerId}/cars")
    public Page<Car> getAllCarsBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                          Pageable pageable) {

        return carRepository.findBySellerId(sellerId, pageable);
    }

    @PostMapping("/sellers/{sellerId}/cars")
    public Car createCar(@PathVariable(value = "sellerId") Long sellerId,
                         @Valid @RequestBody Car car) {

        return sellerRepository.findById(sellerId)
                .map(seller -> {
                    car.setSeller(seller);
                    return carRepository.save(car);
                }).orElseThrow(() -> new ResourceNotFoundException("SellerId " + sellerId + " not found!"));
    }

    @PutMapping("/sellers/{sellerId}/cars/{carId}")
    public Car updateCar(@PathVariable(value = "sellerId") Long sellerId,
                         @PathVariable(value = "carId") Long carId,
                         @Valid @RequestBody Car carReq) {

        if(!sellerRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException("SellerId " + carId + " not found!");
        }

        return carRepository.findById(carId).map(car -> {
            car.setDescription(carReq.getDescription());
            return carRepository.save(car);
        }).orElseThrow(() -> new ResourceNotFoundException("CarId " + carId + "not found"));
    }

    @DeleteMapping("/sellers/{sellerId}/cars/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable(value = "sellerId") Long sellerId,
                                       @PathVariable(value = "carId") Long carId) {

        if(!sellerRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException("SellerId " + carId + " not found!");
        }

        return carRepository.findById(carId).map(car -> {
            carRepository.delete(car);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CarId " + carId + "not found"));
    }
}
