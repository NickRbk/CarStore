package cursor.rybak.store.domain.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "cars")
public class Car extends Audit {
    public Double price;
    public Integer year;
    @Column(name = "country_of_registration")
    public String countryOfRegistration;
    public String type;
    public String model;
    public String mark;
    public String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
}
