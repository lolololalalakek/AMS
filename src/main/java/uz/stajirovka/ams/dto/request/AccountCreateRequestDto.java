package uz.stajirovka.ams.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountType;

import java.util.UUID;

@Builder
public record AccountCreateRequestDto(

        @NotNull(message = "User Id is required")
        UUID userId,

        @NotNull(message = "Currency is required")
        AccountCurrency accountCurrency,

        @NotNull(message = "Account type is required")
        AccountType accountType
) {
}
