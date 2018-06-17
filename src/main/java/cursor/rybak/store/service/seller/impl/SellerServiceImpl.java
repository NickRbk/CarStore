package cursor.rybak.store.service.seller.impl;

import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.domain.repository.seller.SellerRepository;
import cursor.rybak.store.service.seller.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Seller signUp(Seller seller) {
        seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
        return sellerRepository.save(seller);
    }
}
