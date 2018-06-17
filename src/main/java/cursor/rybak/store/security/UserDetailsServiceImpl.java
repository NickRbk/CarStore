package cursor.rybak.store.security;

import cursor.rybak.store.model.Seller;
import cursor.rybak.store.repository.seller.SellerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private SellerRepository sellerRepository;

	public UserDetailsServiceImpl(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Seller seller = sellerRepository.findByEmail(email);
		if (seller == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(seller.getEmail(), seller.getPassword(), emptyList());
	}
}
