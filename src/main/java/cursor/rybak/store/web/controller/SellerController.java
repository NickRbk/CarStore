package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.service.ISellerService;
import cursor.rybak.store.web.dto.CarDTO;
import cursor.rybak.store.web.dto.SellerDTO;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
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
    public List<Car> getAllCarsBySellerId(@PathVariable(value = "sellerId") Long sellerId) {

        return carService.getAllCarsBySellerIdAsStream(sellerId)
                .collect(Collectors.toList());
    }

    @Transactional
    @PostMapping("/{sellerId}/cars")
    public Car addCarBySellerId(@PathVariable(value = "sellerId") Long sellerId,
                                @RequestBody
                                @NotNull
                                @Valid CarDTO carDTO) {

        return carService.add(sellerId, carDTO);
    }
}
