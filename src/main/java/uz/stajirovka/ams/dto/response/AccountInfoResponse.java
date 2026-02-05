package uz.stajirovka.ams.dto.response;

import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.constant.enums.AccountType;

import java.util.UUID;

public record AccountInfoResponse(

        UUID id,

        Long userId,

        String accountNumber,

        AccountStatus accountStatus,

        AccountCurrency accountCurrency,

        AccountType accountType
) {
}
