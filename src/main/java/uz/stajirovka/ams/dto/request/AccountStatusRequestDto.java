package uz.stajirovka.ams.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uz.stajirovka.ams.constant.enums.AccountStatus;

public record AccountStatusRequestDto(
        @NotNull
        AccountStatus accountStatus,

        @NotBlank
        String reason // причина изменения статуса
){
}
