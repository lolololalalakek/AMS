package uz.stajirovka.ams.exception;

import org.springframework.http.HttpStatus;
import uz.stajirovka.ams.constant.enums.ErrorType;

public class AccountAlreadyExistsException  extends BusinessException {
    public AccountAlreadyExistsException (String message) {
        super(20002, message, ErrorType.INTERNAL, HttpStatus.ALREADY_REPORTED);
    }
}

