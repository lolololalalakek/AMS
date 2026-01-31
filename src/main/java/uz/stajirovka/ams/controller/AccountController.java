package uz.stajirovka.ams.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.PageRequestDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.request.ChangeAccountCurrencyRequest;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.service.AccountService;

import java.util.UUID;

@Tag(name = "Account", description = "APIs for managing user accounts")
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Create a new account", description = "Creates a new account for a user with the specified details")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountCreateResponseDto create(@Valid @RequestBody AccountCreateRequestDto request) {
        return accountService.createAccount(request);
    }

    @Operation(summary = "Get all accounts", description = "Returns a paginated list of all accounts")
    @GetMapping
    public Page<AccountInfoResponseDto> getAll(@ModelAttribute PageRequestDto filter) {
        return accountService.getAllAccounts(filter);
    }

    @Operation(summary = "Get account by account number", description = "Returns account information for a given account number")
    @GetMapping("/number/{accountNumber}")
    public AccountInfoResponseDto getByNumber(@PathVariable String accountNumber) {
        return accountService.getAccountInfo(accountNumber);
    }

    @Operation(summary = "Get account balance", description = "Returns the current balance for a given account number")
    @GetMapping("/number/{accountNumber}/balance")
    public BalanceResponseDto balance(@PathVariable String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    @Operation(summary = "Update account status", description = "Updates the status of an account (e.g., ACTIVE, BLOCKED)")
    @PatchMapping("/number/{accountNumber}/status")
    public AccountInfoResponseDto updateStatus(@PathVariable String accountNumber, @RequestParam AccountStatus status) {
        return accountService.updateStatus(accountNumber, status);
    }

    @Operation(summary = "Open account in another currency", description = "Opens a new account for an existing user in a different currency")
    @PostMapping("/change-currency")
    public AccountInfoResponseDto openAccountInAnotherCurrency(
            @RequestBody @Valid ChangeAccountCurrencyRequest request
    ) {
        return accountService.openAccountInAnotherCurrency(request);
    }

    @Operation(summary = "Get accounts by user ID", description = "Returns all accounts belonging to a specific user")
    @GetMapping("/user/{userId}")
    public Page<AccountInfoResponseDto> getAccountByUser(@PathVariable Long userId, @RequestBody PageRequestDto filter) {
        return accountService.getAllAccountsByUserId(userId, filter);
    }

    @Operation(summary = "Get accounts by status", description = "Returns all accounts filtered by account status")
    @GetMapping("/status")
    public Page<AccountInfoResponseDto> getAccountByStatus(@RequestParam AccountStatus status, @RequestBody PageRequestDto filter) {
        return accountService.getAllAccountsByStatus(status, filter);
    }

    @Operation(summary = "Get account by ID", description = "Returns account information for a specific account UUID")
    @GetMapping("/id/{id}")
    public AccountInfoResponseDto getAccountById(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }
}
