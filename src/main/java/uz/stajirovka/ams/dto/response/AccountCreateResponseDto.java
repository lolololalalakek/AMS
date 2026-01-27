package uz.stajirovka.ams.dto.response;

import lombok.Builder;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.constant.enums.AccountType;

import java.time.LocalDateTime;

@Builder
public record AccountCreateResponseDto(

    Long accountNumber,

    AccountStatus accountStatus,

    AccountCurrency accountCurrency,

    AccountType accountType,

    Long balance,

    LocalDateTime createdAt
) {
}
