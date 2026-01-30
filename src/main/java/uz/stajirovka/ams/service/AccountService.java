package uz.stajirovka.ams.service;

import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;

import java.util.UUID;

public interface AccountService {

    AccountCreateResponseDto createAccount(AccountCreateRequestDto requestDto);

    AccountInfoResponseDto getAccountInfo(String accountNumber);

    BalanceResponseDto getBalance(String accountNumber);

    AccountInfoResponseDto blockAccount(String accountNumber);

    AccountInfoResponseDto closeAccount(String accountNumber);

    AccountInfoResponseDto getAccountById(UUID id);
}
