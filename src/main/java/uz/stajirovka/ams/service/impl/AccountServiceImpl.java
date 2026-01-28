package uz.stajirovka.ams.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.dto.FilterDto;
import uz.stajirovka.ams.dto.request.AccountCreateRequestDto;
import uz.stajirovka.ams.dto.response.AccountCreateResponseDto;
import uz.stajirovka.ams.dto.response.AccountInfoResponseDto;
import uz.stajirovka.ams.dto.response.BalanceResponseDto;
import uz.stajirovka.ams.entity.AccountEntity;
import uz.stajirovka.ams.exception.AccountNotFoundException;
import uz.stajirovka.ams.mapper.AccountMapper;
import uz.stajirovka.ams.repository.AccountRepository;
import uz.stajirovka.ams.service.AccountService;

import java.util.List;

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
    public AccountInfoResponseDto updateAccountStatus(Long accountNumber, AccountStatus newStatus) {
        AccountEntity entity = getAccount(accountNumber);
        entity.setAccountStatus(newStatus);
        return accountMapper.toAccountInfoResponse(entity);
    }

    private AccountEntity getAccount(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
    }

    @Override
    @Transactional
    public AccountInfoResponseDto updateCurrencyAccount(Long accountNumber, AccountCurrency accountCurrency) {
        AccountEntity account = getAccount(accountNumber);
        account.setAccountCurrency(accountCurrency);
        account = accountRepository.save(account);
        return accountMapper.toAccountInfoResponse(account);
    }


    @Override
    @Transactional(readOnly = true)
    public List<AccountInfoResponseDto> getAllAccountsByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId).stream()
                .map(accountMapper::toAccountInfoResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountInfoResponseDto> getAllAccountsByStatus(AccountStatus status) {
        return accountRepository.findAllByAccountStatus(status).stream()
                .map(accountMapper::toAccountInfoResponse)
                .toList();
    }

    @Override
    public Page<AccountInfoResponseDto> getAllAccountInfo(FilterDto filterParams) {
        Page<AccountEntity> all = accountRepository.findAll(filterParams.getPageable());
        return all.map(accountMapper::toAccountInfoResponse);
    }
}
