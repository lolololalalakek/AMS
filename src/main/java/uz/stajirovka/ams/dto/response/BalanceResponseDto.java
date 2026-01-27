package uz.stajirovka.ams.dto.response;

import lombok.Builder;
import uz.stajirovka.ams.constant.enums.AccountCurrency;

@Builder
public record BalanceResponseDto(

    Long accountNumber,

    Long balance,

    AccountCurrency accountCurrency
) {
}
