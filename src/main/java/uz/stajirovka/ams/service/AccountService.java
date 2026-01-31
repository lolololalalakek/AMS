package uz.stajirovka.ams.service;

import org.springframework.data.domain.Page;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.PageRequestDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.request.ChangeAccountCurrencyRequest;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;

import java.util.UUID;

public interface AccountService {

    AccountCreateResponseDto createAccount(AccountCreateRequestDto requestDto);

    AccountInfoResponseDto getAccountInfo(String accountNumber);

    BalanceResponseDto getBalance(String accountNumber);


    AccountInfoResponseDto updateStatus(String accountNumber, AccountStatus status);

    AccountInfoResponseDto openAccountInAnotherCurrency(ChangeAccountCurrencyRequest request);

    AccountInfoResponseDto getAccountById(UUID id);

    Page<AccountInfoResponseDto> getAllAccounts(PageRequestDto filter);

    Page<AccountInfoResponseDto> getAllAccountsByUserId(Long userId, PageRequestDto filter);

    Page<AccountInfoResponseDto> getAllAccountsByStatus(AccountStatus status, PageRequestDto filter);
}
