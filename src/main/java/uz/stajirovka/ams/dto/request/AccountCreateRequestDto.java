package uz.stajirovka.ams.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountType;

@Builder
public record AccountCreateRequestDto(

    @NotNull(message = "Currency is required")
    AccountCurrency accountCurrency,

    @NotNull(message = "Account type is required")
    AccountType accountType,

    @PositiveOrZero(message = "Balance must be zero or positive")
    Long balance
) {
}
