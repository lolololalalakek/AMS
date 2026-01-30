package uz.stajirovka.ams.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    }

    @PostMapping("/{accountNumber}/close")
    public AccountInfoResponseDto closeAccount(@PathVariable String accountNumber) {
        return accountService.closeAccount(accountNumber);
    }

    @GetMapping("/{id}")
    public AccountInfoResponseDto getAccountById(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }
}
