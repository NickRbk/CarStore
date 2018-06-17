package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.service.ISellerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
@AllArgsConstructor
public class SellerController {

    private ISellerService sellerService;

    @PostMapping("/sign-up")
    public Seller signUp(@RequestBody Seller seller) {
        return sellerService.signUp(seller);
    }
}
