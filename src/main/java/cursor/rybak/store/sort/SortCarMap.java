package cursor.rybak.store.sort;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.repository.CarRepository;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class SortCarMap implements SortConstants {
    private static SortCarMap instance;

    private final CarRepository carRepository;

    @Getter
    private Map<String, Function<String, Stream<Car>>> sortedMap;

    private SortCarMap(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.sortedMap = generateSortedMap();
    }

    public static SortCarMap getInstance(CarRepository carRepository) {
        if (instance == null) {
            instance = new SortCarMap(carRepository);
        }
        return instance;
    }

    private Map<String, Function<String, Stream<Car>>> generateSortedMap() {
        Map<String, Function<String, Stream<Car>>> sortedMap = new HashMap<>();
        sortedMap.put(YEAR, this::getAllCarOrderByYear);
        sortedMap.put(PRICE, this::getAllCarOrderByPrice);
        sortedMap.put(REGISTRATION, this::getAllCarOrderByRegistration);

        return sortedMap;
    }

    private Stream<Car> getAllCarOrderByYear(String key) {
        return carRepository.getAll().sorted(new OrderByKey(key));
    }

    private Stream<Car> getAllCarOrderByPrice(String key) {
        return carRepository.getAll().sorted(new OrderByKey(key));
    }

    private Stream<Car> getAllCarOrderByRegistration(String key) {
        return carRepository.getAll().sorted(new OrderByKey(key));
    }
}
