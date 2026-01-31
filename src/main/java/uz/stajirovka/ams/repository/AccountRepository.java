package uz.stajirovka.ams.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.stajirovka.ams.constant.enums.AccountStatus;
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
    Optional<AccountEntity> findByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("""
           SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END 
           FROM AccountEntity a 
           WHERE a.accountNumber = :accountNumber
           """)
    boolean existsByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("""
           SELECT a 
           FROM AccountEntity a 
           WHERE a.userId = :userId
           """)
    Page<AccountEntity> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("""
           SELECT COUNT(a) > 0 
           FROM AccountEntity a 
           WHERE a.userId = :userId 
             AND a.accountCurrency = :currency 
             AND a.accountStatus = :status
           """)
    boolean existsByActiveAccount(
            @Param("userId") Long userId,
            @Param("currency") AccountCurrency currency,
            @Param("status") AccountStatus status
    );
    Optional<AccountEntity> findByAccountNumber(long accountNumber);
    Page<AccountEntity> findAllByUserId(Long userId, Pageable pageable);

    Page<AccountEntity> findAllByAccountStatus(AccountStatus status, Pageable pageable);
}
