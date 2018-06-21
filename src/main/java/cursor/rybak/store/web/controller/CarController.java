package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.service.ICarService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class CarController {

    private ICarService carService;

    @Transactional
    @GetMapping()
    public List<Car> getAllCars() {

        return carService.getAllAsStream()
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/car")
    public List<Car> getAllCarsSortedBy(@RequestParam("sortedBy") String key) {

        System.out.println("KEY =================> " + key);
        return carService.getAllSortedByKeyAsStream(key)
                .collect(Collectors.toList());
    }
}
