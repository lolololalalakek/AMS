package uz.stajirovka.ams.exception;

import org.springframework.http.HttpStatus;
import uz.stajirovka.ams.constant.enums.ErrorType;

public class AccountStatusExistsException extends BusinessException{

    public AccountStatusExistsException( String message) {
        super(20005, message, ErrorType.INTERNAL, HttpStatus.CONFLICT);
    }
}
