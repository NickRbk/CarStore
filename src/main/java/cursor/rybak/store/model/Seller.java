package cursor.rybak.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "sellers")
public class Seller extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "INVALID EMAIL")
    @Column(unique = true)
    private String email;

    @Column(name = "first_name")
    @Size(min = 3, max = 15)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 15)
    private String lastName;

    @NotNull
    private String password;

    @Column(name = "phone_number")
    @Size(min = 10, max = 20)
    private String phoneNumber;
}
