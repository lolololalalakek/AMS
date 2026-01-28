package uz.stajirovka.ams.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.entity.AccountEntity;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    // Поиск по String, так как в номере счета 20 цифр (могут быть ведущие нули)
    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    // Метод для проверки уникальности при генерации нового номера
    boolean existsByAccountNumber(String accountNumber);

    // Поиск всех счетов одного пользователя
    Page<AccountEntity> findAllByUserId(Long userId, Pageable pageable);

    // Проверка, нет ли у пользователя уже открытого счета в определенной валюте, чтобы запретить дубликаты
    boolean existsByUserIdAndAccountCurrencyAndAccountStatus(
            Long userId,
            AccountCurrency currency,
            AccountStatus status
    );

    // Поиск по части номера счета
    Page<AccountEntity> findAllByAccountNumberStartingWith(String prefix, Pageable pageable);

    // Подсчет общей суммы пользователя по всем его счетам в конкретной валюте
    @Query("SELECT SUM(a.balance) FROM AccountEntity a WHERE a.userId = :userId AND a.accountCurrency = :currency")
    java.math.BigDecimal getTotalBalanceByUserIdAndCurrency(Long userId, AccountCurrency currency);
}