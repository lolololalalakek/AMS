package uz.stajirovka.ams.exception;

import org.springframework.http.HttpStatus;
import uz.stajirovka.ams.constant.enums.ErrorType;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException(String message) {
        super(20001, message, ErrorType.INTERNAL, HttpStatus.CONFLICT);
    }
}
