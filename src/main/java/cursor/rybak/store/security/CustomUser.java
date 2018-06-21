package cursor.rybak.store.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class CustomUser extends User {
    @Getter
    private final Long userID;

    public CustomUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities, Long userID)
    {
        super(username, password, true, true, true, true, authorities);
        this.userID = userID;
    }
}
