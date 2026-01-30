package uz.stajirovka.ams.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.entity.AccountEntity;

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

    @Query("""
           SELECT a
           FROM AccountEntity a 
           WHERE a.accountNumber LIKE :prefix%
           """)
    Page<AccountEntity> findAllByAccountNumberStartingWith(@Param("prefix") String prefix, Pageable pageable);

    // Пример использования EntityGraph для жадной загрузки, если появятся связи
    @EntityGraph(attributePaths = {"userInfo"})
    @Query("""
            SELECT a 
            FROM AccountEntity a 
            WHERE a.id = :id
           """)
    Optional<AccountEntity> findWithDetailsById(@Param("id") UUID id);
}