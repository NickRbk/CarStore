package cursor.rybak.store.repository.car;

import cursor.rybak.store.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Page<Car> findBySellerId(Long sellerId, Pageable pageable);
}
