package cursor.rybak.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "car_kind_id")
    private CarKind carKind;

    private String country_of_registration;
    private String description;
    private LocalDate time_on_app;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
