package uz.stajirovka.ams.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.PageRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.service.AccountService;

import java.util.UUID;

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
    public AccountInfoResponseDto getAccountInfo(@PathVariable String accountNumber) {
        return accountService.getAccountInfo(accountNumber);
    }

    @GetMapping("/{accountNumber}/balance")
    public BalanceResponseDto getBalance(@PathVariable String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    @PostMapping("/{accountNumber}/block")
    public AccountInfoResponseDto blockAccount(@PathVariable String accountNumber) {
        return accountService.blockAccount(accountNumber);

    @PatchMapping("/{accountNumber}/status")
    public AccountInfoResponseDto updateAccountStatus(@PathVariable Long accountNumber, @RequestParam AccountStatus status) {
        return accountService.newStatus(accountNumber, status);
    }


    @PatchMapping("/{accountNumber}/currency")
    public AccountInfoResponseDto updateCurrency(@PathVariable Long accountNumber, @RequestBody AccountCurrency accountCurrency) {
        return accountService.updateCurrencyAccount(accountNumber, accountCurrency);
    }

    @PostMapping("/{accountNumber}/close")
    public AccountInfoResponseDto closeAccount(@PathVariable String accountNumber) {
        return accountService.closeAccount(accountNumber);
    @GetMapping("/user/{userId}")
    public Page<AccountInfoResponseDto> getAllAccountsByUserId(@PathVariable Long userId, PageRequestDto filter) {
        return accountService.getAllAccountsByUserId(userId, filter);
    }

    @GetMapping("/{id}")
    public AccountInfoResponseDto getAccountById(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/status")
    public Page<AccountInfoResponseDto> getAllAccountsByStatus(@RequestParam AccountStatus status, PageRequestDto filter) {
        return accountService.getAllAccountsByStatus(status, filter);
    }

}
