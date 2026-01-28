package uz.stajirovka.ams.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.FilterDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountCreateResponseDto createAccount(@Valid @RequestBody AccountCreateRequestDto requestDto) {
        return accountService.createAccount(requestDto);
    }

    @GetMapping()
    public Page<AccountInfoResponseDto> getAllAccounts(FilterDto filterParams) {
        return (accountService.getAllAccountInfo(filterParams));
    }

    @GetMapping("/{accountNumber}")
    public AccountInfoResponseDto getAccountInfo(@PathVariable Long accountNumber) {
        return accountService.getAccountInfo(accountNumber);
    }

    @GetMapping("/{accountNumber}/balance")
    public BalanceResponseDto getBalance(@PathVariable Long accountNumber) {
        return accountService.getBalance(accountNumber);
    }


    @PatchMapping("/{accountNumber}/status")
    public AccountInfoResponseDto updateAccountStatus(@PathVariable Long accountNumber, @RequestParam AccountStatus status) {
        return (accountService.updateAccountStatus(accountNumber, status));
    }


    @PatchMapping("/{accountNumber}/currency")
    public AccountInfoResponseDto updateCurrency(@PathVariable Long accountNumber, @RequestBody AccountCurrency accountCurrency) {
        return accountService.updateCurrencyAccount(accountNumber, accountCurrency);
    }

    @GetMapping("/user/{userId}")
    public List<AccountInfoResponseDto> getAllAccountsByUserId(@PathVariable Long userId) {
        return accountService.getAllAccountsByUserId(userId);
    }

    @GetMapping("/status")
    public List<AccountInfoResponseDto> getAllAccountsByStatus(@RequestParam AccountStatus status) {
        return accountService.getAllAccountsByStatus(status);
    }
}
