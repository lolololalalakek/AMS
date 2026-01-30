package uz.stajirovka.ams.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.PageRequestDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.service.AccountService;

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
    public Page<AccountInfoResponseDto> getAllAccounts(PageRequestDto filterParams) {
        return accountService.getAllAccountInfo(filterParams);
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
        return accountService.newStatus(accountNumber, status);
    }


    @PatchMapping("/{accountNumber}/currency")
    public AccountInfoResponseDto updateCurrency(@PathVariable Long accountNumber, @RequestBody AccountCurrency accountCurrency) {
        return accountService.updateCurrencyAccount(accountNumber, accountCurrency);
    }

    @GetMapping("/user/{userId}")
    public Page<AccountInfoResponseDto> getAllAccountsByUserId(@PathVariable Long userId, PageRequestDto filter) {
        return accountService.getAllAccountsByUserId(userId, filter);
    }

    @GetMapping("/status")
    public Page<AccountInfoResponseDto> getAllAccountsByStatus(@RequestParam AccountStatus status, PageRequestDto filter) {
        return accountService.getAllAccountsByStatus(status, filter);
    }

}
