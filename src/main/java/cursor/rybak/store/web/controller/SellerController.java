package cursor.rybak.store.web.controller;

import cursor.rybak.store.domain.model.Car;
import cursor.rybak.store.domain.model.Seller;
import cursor.rybak.store.exception.UpdateException;
import cursor.rybak.store.security.SecurityConstants;
import cursor.rybak.store.security.constants.JWTConstants;
import cursor.rybak.store.service.ICarService;
import cursor.rybak.store.service.ISellerService;
import cursor.rybak.store.web.dto.CarDTO;
import cursor.rybak.store.web.dto.SellerDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SellerController implements SecurityConstants, JWTConstants {

    private ISellerService sellerService;
    private ICarService carService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public Seller signUp(@RequestBody @NotNull @Valid SellerDTO sellerDTO) {
        return sellerService.signUp(sellerDTO);
    }

    @Transactional
    @GetMapping("/cars")
    public List<Car> getAllCarsBySellerId(@RequestHeader(HEADER_STRING) String token) {
        return carService.getAllCarsBySellerIdAsStream(getIdFromToken(token))
                .collect(Collectors.toList());
    }

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cars")
    public void addCarBySellerId(@RequestBody @NotNull @Valid List<CarDTO> carDTOs,
                                 @RequestHeader(HEADER_STRING) String token) {
        carService.add(getIdFromToken(token), carDTOs);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/cars/{carId}")
    public void deleteCarByCarId(@PathVariable(value = "carId") Long carId,
                                 @RequestHeader(HEADER_STRING) String token) {
        carService.delete(getIdFromToken(token), carId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/cars/{carId}")
    public void updateCar(@PathVariable Long carId,
                          @RequestBody @NotNull @Valid CarDTO carDTO,
                          @RequestHeader(HEADER_STRING) String token) {
        if (carDTO.getId() != null) {
            carService.update(getIdFromToken(token), carId, carDTO);
        } else throw new UpdateException();
    }


    /**
     * Get userID from token
     *
     * @param token JWT token
     * @return userID
     */
    private Long getIdFromToken(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();

        return Long.parseLong(body.get(USER_ID).toString());
    }
}
