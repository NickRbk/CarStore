package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.service.ICarService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class CarController {

    private ICarService carService;

    @GetMapping("/sellers/{sellerId}/cars")
    public Page<Car> getAllCarsBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                          Pageable pageable) {

        return carService.getAllCarsBySellerId(sellerId, pageable);
    }

    @PostMapping("/sellers/{sellerId}/cars")
    public Car addCarBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                @Valid @RequestBody Car car) {

        return carService.add(sellerId, car);
    }

    @PutMapping("/sellers/{sellerId}/cars/{carId}")
    public Car updateCarByCarId(@PathVariable(value = "sellerId") Long sellerId,
                                @PathVariable(value = "carId") Long carId,
                                @Valid @RequestBody Car carReq) {

        return carService.update(sellerId, carId, carReq);
    }

    @DeleteMapping("/sellers/{sellerId}/cars/{carId}")
    public ResponseEntity<?> deleteCarByCarId(@PathVariable(value = "sellerId") Long sellerId,
                                              @PathVariable(value = "carId") Long carId) {

        return carService.delete(sellerId, carId);
    }
}