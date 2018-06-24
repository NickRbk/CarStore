package cursor.rybak.store.service;

import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.web.dto.SellerDTO;

public interface ISellerService {
    Seller signUp(SellerDTO sellerDTO);
}
