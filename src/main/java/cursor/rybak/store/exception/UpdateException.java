package cursor.rybak.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ID MISSED: CAN'T UPDATE ENTITY WITHOUT ID!")
public class UpdateException extends RuntimeException {
}
