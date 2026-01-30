package uz.stajirovka.ams.service;

import org.springframework.data.domain.Page;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.PageRequestDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;

public interface AccountService {

    AccountCreateResponseDto createAccount(AccountCreateRequestDto requestDto);

    AccountInfoResponseDto getAccountInfo(Long accountNumber);

    BalanceResponseDto getBalance(Long accountNumber);

    AccountInfoResponseDto newStatus(Long accountNumber, AccountStatus newStatus);

    AccountInfoResponseDto updateCurrencyAccount(Long accountNumber, AccountCurrency accountCurrency);

    Page<AccountInfoResponseDto> getAllAccountsByUserId(Long userId, PageRequestDto filter);

    Page<AccountInfoResponseDto> getAllAccountsByStatus(AccountStatus status, PageRequestDto filter);

    Page<AccountInfoResponseDto> getAllAccountInfo(PageRequestDto filterParams);

}