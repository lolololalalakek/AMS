package uz.stajirovka.ams.dto;

import lombok.Builder;
import uz.stajirovka.ams.constant.enums.ErrorType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorDto(
        int code,
        String message,
        ErrorType type,
        List<String> validationErrors
) {
}
