package cursor.rybak.store.domain.repository;

import cursor.rybak.store.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
	Seller findByEmail(String email);
}
