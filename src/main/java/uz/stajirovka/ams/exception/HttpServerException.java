package uz.stajirovka.ams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import uz.stajirovka.ams.constant.enums.ErrorType;


public class HttpServerException extends BusinessException {
    public HttpServerException(String message, HttpStatusCode httpStatusCode) {
        super(40001, message, ErrorType.EXTERNAL, HttpStatus.valueOf(httpStatusCode.value()));
    }
}
