package uz.stajirovka.ams.dto.response;

import lombok.Builder;
import uz.stajirovka.ams.constant.enums.AccountCurrency;

import java.math.BigDecimal;

@Builder
public record BalanceResponseDto(

    String  accountNumber,

    BigDecimal balance,

    AccountCurrency accountCurrency
) {
}
