package cursor.rybak.store.service.impl;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.domain.repository.CarRepository;
import cursor.rybak.store.domain.repository.SellerRepository;
import cursor.rybak.store.exception.InvalidParameterException;
import cursor.rybak.store.exception.NotFoundException;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.sort.SortCarMap;
import cursor.rybak.store.web.dto.CarDTO;
import cursor.rybak.store.web.dto.EntityAdapter;
import lombok.AllArgsConstructor;
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
        if (isValidCriteria(key)) {
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
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(NotFoundException::new);
        carRepository.saveAll(getCarsToSave(carDTOs, seller));
    }

    @Override
    public void delete(Long sellerId, Long carId) {
        Car car = getCar(carId, sellerId).orElseThrow(NotFoundException::new);
        carRepository.delete(car);
    }

    @Override
    public void update(Long sellerId, Long carId, CarDTO carDTO) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(NotFoundException::new);
        carRepository.save(EntityAdapter.getCarFromCarDTO(carDTO, seller));
    }

    private boolean isValidCriteria(String criteria) {
        return SORT_CRITERIA.stream()
                .anyMatch(c -> c.equals(criteria));
    }

    private List<Car> getCarsToSave(List<CarDTO> carDTOs, Seller seller) {
        List<Car> cars = new ArrayList<>();
        carDTOs.forEach(carDTO -> cars.add(EntityAdapter.getCarFromCarDTO(carDTO, seller)));
        return cars;
    }
}
