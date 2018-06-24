package cursor.rybak.store.security;

import cursor.rybak.store.domain.model.Seller;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

class CustomUser extends User {

    @Getter
    private final Seller seller;

    CustomUser(String username,
                      String password,
                      Collection<? extends GrantedAuthority> authorities,
                      Seller seller) {
        super(username, password, authorities);
        this.seller = seller;
    }
}
