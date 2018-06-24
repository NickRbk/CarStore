package cursor.rybak.store.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "cursor.rybak.store.web.controller")
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidParameterException.class, NotFoundException.class, UpdateException.class})
    public void handleInvalidDataExceptions() {
        log.error("====== INVALID DATA EXCEPTION ======");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UnauthorizedException.class})
    public void handleUnauthorizedExceptions() {
        log.error("====== UNAUTHORIZED EXCEPTION ======");
    }
}