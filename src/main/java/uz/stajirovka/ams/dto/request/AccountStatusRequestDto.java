package uz.stajirovka.ams.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uz.stajirovka.ams.constant.enums.AccountStatus;

public record AccountStatusRequestDto(
        @NotNull(message = "Status is required")
        AccountStatus accountStatus,

        @NotBlank(message = "Reason for status change must not be empty")
        String reason
) {}
