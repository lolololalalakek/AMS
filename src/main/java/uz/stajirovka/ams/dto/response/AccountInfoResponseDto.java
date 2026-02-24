package uz.stajirovka.ams.dto.response;

import lombok.Builder;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.constant.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record AccountInfoResponseDto(

        UUID id,

        String accountNumber,

        AccountStatus accountStatus,

        AccountCurrency accountCurrency,

        AccountType accountType,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
