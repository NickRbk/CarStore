package cursor.rybak.store.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "cars")
public class Car extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer price;

    private Integer year;

    @Column(name = "country_of_registration")
    @NotEmpty
    @NotNull
    private String countryOfRegistration;

    @NotEmpty
    @NotNull
    private String type;

    @NotEmpty
    @NotNull
    private String model;

    @NotEmpty
    @NotNull
    private String mark;

    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seller seller;
}
