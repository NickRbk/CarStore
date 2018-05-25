package cursor.rybak.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "sellers")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private Integer year;
    private String country_of_registration;
    private String description;
    private LocalDate time_on_app;
}
