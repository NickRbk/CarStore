package cursor.rybak.store.service.impl;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.domain.repository.CarRepository;
import cursor.rybak.store.domain.repository.SellerRepository;
import cursor.rybak.store.exception.InvalidParameterException;
import cursor.rybak.store.exception.NotFoundException;
import cursor.rybak.store.exception.UnauthorizedException;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.sort.SortCarMap;
import cursor.rybak.store.web.dto.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static cursor.rybak.store.sort.SortConstants.SORT_CRITERIA;

@Service
@AllArgsConstructor
public class CarService implements ICarService {

    private CarRepository carRepository;
    private SellerRepository sellerRepository;

    @Override
    public Optional<Car> getCar(Long carId, Long sellerId) {
        return carRepository.getCarByIdAndSellerId(carId, sellerId);
    }

    @Override
    public Stream<Car> getAllAsStream() {
        return carRepository.getAll();
    }

    @Override
    public Stream<Car> getAllSortedByKeyAsStream(String key) {

        if( isValidCriteria(key) ) {
            return SortCarMap.getInstance(carRepository)
                    .getSortedMap()
                    .get(key)
                    .get();
        } else throw new InvalidParameterException();
    }

    @Override
    public Stream<Car> getAllCarsBySellerIdAsStream(Long sellerId) {
        return carRepository.findBySellerId(sellerId);
    }

    @Override
    public void add(Long sellerId, List<CarDTO> carDTOs) {
        List<Car> newCars = new ArrayList<>();

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(NotFoundException::new);

        carDTOs.forEach(carDTO -> newCars.add(
                carRepository.save(Car.builder()
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
        ));
    }

    @Override
    public void delete(Long sellerId, Long carId, String email) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new NotFoundException();
        }

        carRepository.findById(carId)
                .map(car -> {
                    if(car.getSeller().getEmail().equals(email)) {
                        carRepository.delete(car);
                        return ResponseEntity.ok()
                                .body("CarId " + carId + " deleted successfully");
                    } else throw new UnauthorizedException();
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(Long sellerId, Long carId, Car updatedCar, String email) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new NotFoundException();
        }

        carRepository
                .findById(carId)
                .map(car -> {
                    if(car.getSeller().getEmail().equals(email)) {
                        return carRepository.save(updatedCar);
                    } else throw new UnauthorizedException();
                })
                .orElseThrow(NotFoundException::new);
    }

    private boolean isValidCriteria(String criteria) {
        return SORT_CRITERIA.stream()
                .anyMatch(c -> c.equals(criteria));
    }
}
