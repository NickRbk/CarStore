package cursor.rybak.store.repository;

import cursor.rybak.store.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
	Seller findByUsername(String username);
}
