package uz.stajirovka.ams.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.entity.AccountEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    @Query("""
        SELECT a
        FROM AccountEntity a
        WHERE a.accountNumber = :accountNumber
    """)
    Optional<AccountEntity> findByAccountNumber(
            @Param("accountNumber") String accountNumber
    );

    @Query("""
    SELECT COUNT(a) > 0
    FROM AccountEntity a
    WHERE a.userId = :userId
      AND a.accountCurrency = :currency
""")
    boolean existsByUserIdAndCurrency(
            @Param("userId") Long userId,
            @Param("currency") AccountCurrency currency
    );


    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
        FROM AccountEntity a
        WHERE a.accountNumber = :accountNumber
    """)
    boolean existsByAccountNumber(
            @Param("accountNumber") String accountNumber
    );

    @Query("""
        SELECT a
        FROM AccountEntity a
        WHERE a.userId = :userId
    """)
    List<AccountEntity> findAllByUserId(
            @Param("userId") Long userId);

    @Query("""
        SELECT a
        FROM AccountEntity a
        WHERE a.accountStatus = :status
    """)
    Page<AccountEntity> findAllByAccountStatus(
            @Param("status") AccountStatus status,
            Pageable pageable
    );

    @Query("""
                SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
                FROM AccountEntity a
                WHERE a.userId = :userId
                  AND a.accountCurrency = :currency
                  AND a.accountStatus = :status
            """)
    boolean existsActiveAccount(
            @Param("userId") Long userId,
            @Param("currency") AccountCurrency currency,
            @Param("status") AccountStatus status
    );

    boolean existsByIdAndAccountCurrency(UUID id, AccountCurrency currency);

    boolean existsByIdAndUserId(UUID id, Long userId);

}
