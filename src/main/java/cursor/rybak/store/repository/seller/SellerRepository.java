package cursor.rybak.store.repository.seller;

import cursor.rybak.store.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
	Seller findByEmail(String email);
}
