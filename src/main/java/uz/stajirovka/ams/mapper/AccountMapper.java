package uz.stajirovka.ams.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.entity.AccountEntity;

import static org.mapstruct.ReportingPolicy.IGNORE; // Лучше IGNORE для чистого лога

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccountEntity toEntity(AccountCreateRequestDto requestDto);

    AccountCreateResponseDto toCreateResponse(AccountEntity entity);

    AccountInfoResponseDto toAccountInfoResponse(AccountEntity entity);

    BalanceResponseDto toBalanceResponse(AccountEntity entity);
}
