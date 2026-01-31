package uz.stajirovka.ams.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import uz.stajirovka.ams.dto.ErrorDto;
import uz.stajirovka.ams.exception.BusinessException;

import static uz.stajirovka.ams.constant.enums.Error.*;
import static uz.stajirovka.ams.constant.enums.ErrorType.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Твои бизнес-исключения (AccountNotFound и т.д.)
    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorDto> handleApplicationException(BusinessException ex) {
        log.error("Business Error: {}", ex.getMessage());

        var errorBody = ErrorDto.builder()
                .code(ex.getCode())
                .type(ex.getErrorType())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(errorBody);
    }

    // 2. Валидация полей (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage());

        var validationErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .toList();

        var error = ErrorDto.builder()
                .type(VALIDATION)
                .code(VALIDATION_ERROR.getCode())
                .message(VALIDATION_ERROR.getMessage())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    // 3. Ошибки внешних вызовов (HttpClient)
    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<ErrorDto> handleHttpClientException(final HttpClientErrorException ex) {
        log.error("External Client Error: {}", ex.getMessage());

        var error = ErrorDto.builder()
                .code(TRANSACTION_FAILED.getCode()) // Или добавь EXTERNAL_SERVICE_ERROR
                .type(EXTERNAL)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    // 4. Ошибки формата (невалидный JSON)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDto> handleJsonException(final HttpMessageNotReadableException ex) {
        log.error("JSON Error: {}", ex.getMessage());

        var error = ErrorDto.builder()
                .type(INTERNAL)
                .code(JSON_NOT_VALID.getCode())
                .message("Invalid JSON format")
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    // 5. Глобальный перехватчик всех остальных ошибок
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDto> handleGeneralException(final Exception ex) {
        log.error("Unexpected System Error: ", ex);

        var error = ErrorDto.builder()
                .code(INTERNAL_SERVICE_ERROR.getCode())
                .type(INTERNAL)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // Здесь можно добавить остальные методы (NoHandlerFound, MethodNotSupported и т.д.) по аналогии
}