package uz.stajirovka.ams.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorDto(
    String message,
    int status,
    LocalDateTime timestamp,
    String path
) {
}
