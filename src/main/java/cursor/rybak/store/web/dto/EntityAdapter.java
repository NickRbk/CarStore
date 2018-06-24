package cursor.rybak.store.web.dto;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EntityAdapter {
    public static Car getCarFromCarDTO(Long carId, CarDTO carDTO, Seller seller) {
        return Car.builder()
                .id(carId)
                .price(carDTO.getPrice())
                .year(carDTO.getYear())
                .countryOfRegistration(carDTO.getCountryOfRegistration())
                .type(carDTO.getType())
                .model(carDTO.getModel())
                .mark(carDTO.getMark())
                .description(carDTO.getDescription())
                .seller(seller)
                .build();
    }

    public static Seller getSellerFromSellerDTO(SellerDTO sellerDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return Seller.builder()
                .email(sellerDTO.getEmail())
                .firstName(sellerDTO.getFirstName())
                .lastName(sellerDTO.getLastName())
                .password(bCryptPasswordEncoder.encode(sellerDTO.getPassword()))
                .phoneNumber(sellerDTO.getPhoneNumber())
                .build();
    }
}
