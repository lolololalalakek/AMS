package uz.stajirovka.ams.exception;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import uz.stajirovka.ams.constant.enums.ErrorType;


@Getter
@ToString
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BusinessException extends RuntimeException {

    int code;
    String message;
    HttpStatus status;
    ErrorType errorType;

    public BusinessException(int code, String message, ErrorType errorType, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
        this.message = message;
        this.errorType = errorType;
    }
}