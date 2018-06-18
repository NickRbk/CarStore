package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.web.dto.CarDTO;
import cursor.rybak.store.web.dto.SellerDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@AllArgsConstructor
public class CarController {

//    private ICarService carService;
//
//    @GetMapping("/sellers/{sellerId}/cars")
//    public Page<Car> getAllCarsBySellerId(@PathVariable(value = "sellerId") Long sellerId,
//                                          Pageable pageable) {
//
//        return carService.getAllCarsBySellerId(sellerId, pageable);
//    }
//
//    @PostMapping("/sellers/{sellerId}/cars")
//    public Car addCarBySellerId(@PathVariable(value = "sellerId") Long sellerId,
//                                @RequestBody
//                                @NotNull
//                                @Valid CarDTO carDTO) {
//
//        return carService.add(sellerId, carDTO);
//    }

//    @PutMapping("/sellers/{sellerId}/cars/{carId}")
//    public Car updateCarByCarId(@PathVariable(value = "sellerId") Long sellerId,
//                                @PathVariable(value = "carId") Long carId,
//                                @Valid @RequestBody Car carReq) {
//
//        return carService.update(sellerId, carId, carReq);
//    }
}
