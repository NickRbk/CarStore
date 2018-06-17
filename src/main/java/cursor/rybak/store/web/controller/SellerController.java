package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.service.ISellerService;
import cursor.rybak.store.web.dto.CarDTO;
import cursor.rybak.store.web.dto.SellerDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @GetMapping("/{sellerId}/cars")
    public Page<Car> getAllCarsBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                          Pageable pageable) {

        return carService.getAllCarsBySellerId(sellerId, pageable);
    }

    @PostMapping("/{sellerId}/cars")
    public Car addCarBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                @RequestBody
                                @NotNull
                                @Valid CarDTO carDTO) {

        return carService.add(sellerId, carDTO);
    }
}
