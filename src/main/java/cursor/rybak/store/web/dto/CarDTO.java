package cursor.rybak.store.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarDTO {

    @NotNull(message = "price can not be NULL")
    private Double price;

    @NotNull(message = "year can not be NULL")
    @Size(min = 4, message = "year min size 4")
    private Integer year;

    @NotNull(message = "countryOfRegistration can not be NULL")
    @Size(min = 2, message = "countryOfRegistration min size 2 chars")
    private String countryOfRegistration;

    @NotNull(message = "type can not be NULL")
    @Size(min = 2, message = "type min size 2 chars")
    private String type;

    @NotNull(message = "model can not be NULL")
    @Size(min = 2, message = "model min size 2 chars")
    private String model;

    @NotNull(message = "mark can not be NULL")
    @Size(min = 2, message = "mark min size 2 chars")
    private String mark;

    @NotNull(message = "description can not be NULL")
    @Size(min = 8, message = "description min size 8 chars")
    private String description;
}
