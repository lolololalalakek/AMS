package uz.stajirovka.ams.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.PageRequestDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.request.ChangeAccountCurrencyRequest;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.entity.AccountEntity;
import uz.stajirovka.ams.exception.AccountAlreadyExistsException;
import uz.stajirovka.ams.exception.AccountNotFoundException;
import uz.stajirovka.ams.mapper.AccountMapper;
import uz.stajirovka.ams.repository.AccountRepository;
import uz.stajirovka.ams.service.AccountService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountCreateResponseDto createAccount(AccountCreateRequestDto requestDto) {
        AccountEntity entity = accountMapper.toEntity(requestDto);
        entity.setAccountStatus(AccountStatus.ACTIVE);
        return accountMapper.toCreateResponse(accountRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountInfoResponseDto getAccountInfo(String accountNumber) {
        return accountMapper.toAccountInfoResponse(getAccount(accountNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public BalanceResponseDto getBalance(String accountNumber) {
        return accountMapper.toBalanceResponse(getAccount(accountNumber));
    }

    @Override
    public AccountInfoResponseDto updateStatus(String accountNumber, AccountStatus status) {
        AccountEntity account = getAccount(accountNumber);
        account.setAccountStatus(status);
        return accountMapper.toAccountInfoResponse(account);
    }

    @Override
    @Transactional
    public AccountInfoResponseDto openAccountInAnotherCurrency(ChangeAccountCurrencyRequest request) {

        AccountEntity oldAccount = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (oldAccount.getAccountCurrency() == request.getCurrency()) {
            throw new AccountAlreadyExistsException("Account already has this currency");
        }

        boolean exists = accountRepository.existsByUserIdAndCurrency(
                oldAccount.getUserId(),
                request.getCurrency()
        );

        if (exists) {
            throw new AccountAlreadyExistsException("Account with this currency already exists");
        }

        AccountEntity newAccount = AccountEntity.builder()
                .userId(oldAccount.getUserId())
                .accountCurrency(request.getCurrency())
                .accountStatus(AccountStatus.ACTIVE)
                .build();


        return accountMapper.toAccountInfoResponse(newAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountInfoResponseDto getAccountById(UUID id) {
        return accountRepository.findById(id)
                .map(accountMapper::toAccountInfoResponse)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountInfoResponseDto> getAllAccounts(PageRequestDto filter) {
        return accountRepository.findAll(filter.getPageable())
                .map(accountMapper::toAccountInfoResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountInfoResponseDto> getAllAccountsByUserId(Long userId, PageRequestDto filter) {
        return accountRepository.findAllByUserId(userId, filter.getPageable())
                .map(accountMapper::toAccountInfoResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountInfoResponseDto> getAllAccountsByStatus(AccountStatus status, PageRequestDto filter) {
        return accountRepository.findAllByAccountStatus(status, filter.getPageable())
                .map(accountMapper::toAccountInfoResponse);
    }

    private AccountEntity getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found: " + accountNumber));
    }
}
