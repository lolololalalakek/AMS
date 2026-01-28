package uz.stajirovka.ams.service;

import org.springframework.data.domain.Page;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.FilterDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;

import java.util.List;

public interface AccountService {

    AccountCreateResponseDto createAccount(AccountCreateRequestDto requestDto);

    AccountInfoResponseDto getAccountInfo(Long accountNumber);

    BalanceResponseDto getBalance(Long accountNumber);

    AccountInfoResponseDto updateAccountStatus(Long accountNumber, AccountStatus newStatus);

    AccountInfoResponseDto updateCurrencyAccount(Long accountNumber, AccountCurrency accountCurrency);


    List<AccountInfoResponseDto> getAllAccountsByUserId(Long userId);

    List<AccountInfoResponseDto> getAllAccountsByStatus(AccountStatus status);

    Page<AccountInfoResponseDto> getAllAccountInfo(FilterDto filterParams);

}