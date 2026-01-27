package uz.stajirovka.ams.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.entity.AccountEntity;
import uz.stajirovka.ams.exception.AccountNotFoundException;
import uz.stajirovka.ams.mapper.AccountMapper;
import uz.stajirovka.ams.repository.AccountRepository;
import uz.stajirovka.ams.service.AccountService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public AccountCreateResponseDto createAccount(AccountCreateRequestDto requestDto) {
        AccountEntity entity = accountMapper.toEntity(requestDto);
        entity.setAccountStatus(AccountStatus.ACTIVE);
        entity.setBalance(0L);
        return accountMapper.toCreateResponse(accountRepository.save(entity));
    }

    @Override
    public AccountInfoResponseDto getAccountInfo(Long accountNumber) {
        return accountMapper.toAccountInfoResponse(getAccount(accountNumber));
    }

    @Override
    public BalanceResponseDto getBalance(Long accountNumber) {
        return accountMapper.toBalanceResponse(getAccount(accountNumber));
    }

    @Override
    @Transactional
    public AccountInfoResponseDto blockAccount(Long accountNumber) {
        AccountEntity entity = getAccount(accountNumber);
        entity.setAccountStatus(AccountStatus.FREEZED);
        return accountMapper.toAccountInfoResponse(entity);
    }

    @Override
    @Transactional
    public AccountInfoResponseDto closeAccount(Long accountNumber) {
        AccountEntity entity = getAccount(accountNumber);
        entity.setAccountStatus(AccountStatus.INACTIVE);
        return accountMapper.toAccountInfoResponse(entity);
    }

    private AccountEntity getAccount(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
    }
}
