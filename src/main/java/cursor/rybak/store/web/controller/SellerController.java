package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.exception.UnauthorizedException;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.service.ISellerService;
import cursor.rybak.store.web.dto.CarDTO;
import cursor.rybak.store.web.dto.SellerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/sellers")
@AllArgsConstructor
public class SellerController {

    private ISellerService sellerService;
    private ICarService carService;

    @PostMapping("/sign-up")
    public Seller signUp(@RequestBody
                         @NotNull
                         @Valid SellerDTO sellerDTO) {

        return sellerService.signUp(sellerDTO);
    }

    @Transactional
    @GetMapping("/{sellerId}/cars")
    public List<Car> getAllCarsBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                          Authentication authentication) {


        if (isAuthorized(authentication, sellerId)) {
            return carService.getAllCarsBySellerIdAsStream(sellerId)
                    .collect(Collectors.toList());
        } else throw new UnauthorizedException();
    }

    @Transactional
    @PostMapping("/{sellerId}/cars")
    public List<Car> addCarBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                      @RequestBody
                                      @NotNull
                                      @Valid List<CarDTO> carDTOs,
                                      Authentication authentication) {

        if (isAuthorized(authentication, sellerId)) {
            return carService.add(sellerId, carDTOs);
        } else throw new UnauthorizedException();
    }

    @DeleteMapping("/{sellerId}/cars/{carId}")
    public ResponseEntity<?> deleteCarByCarId(@PathVariable(value = "sellerId") Long sellerId,
                                              @PathVariable(value = "carId") Long carId,
                                              Authentication authentication) {

        if (isAuthorized(authentication, sellerId)) {
            return carService.delete(sellerId, carId);
        } else throw new UnauthorizedException();
    }

    @PatchMapping("/{sellerId}/cars/{carId}")
    public Car updateCar(@PathVariable Long sellerId,
                         @PathVariable Long carId,
                         @RequestBody Map<String, Object> fields,
                         Authentication authentication) {


        if (isAuthorized(authentication, sellerId)) {
            Car car = carService.getCar(carId, sellerId);

            fields.forEach((K, V) -> {
                Field field = ReflectionUtils.findField(Car.class, K);
                ReflectionUtils.setField(field, car, V);
            });

            return carService.update(sellerId, carId, car);
        } else throw new UnauthorizedException();
    }

    private Boolean isAuthorized(Authentication authentication, Long id) {
        return sellerService.getSellerId((String) authentication.getPrincipal()).equals(id);
    }
}
