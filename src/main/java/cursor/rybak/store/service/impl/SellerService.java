package cursor.rybak.store.service.impl;

import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.domain.repository.SellerRepository;
import cursor.rybak.store.service.ISellerService;
import cursor.rybak.store.web.dto.EntityAdapter;
import cursor.rybak.store.web.dto.SellerDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerService implements ISellerService {
    private SellerRepository sellerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Seller signUp(SellerDTO sellerDTO) {
        return sellerRepository.save(EntityAdapter.getSellerFromSellerDTO(sellerDTO, bCryptPasswordEncoder));
    }
}