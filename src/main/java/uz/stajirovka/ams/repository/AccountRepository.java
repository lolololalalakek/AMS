package uz.stajirovka.ams.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountNumber(long accountNumber);
    Page<AccountEntity> findAllByUserId(Long userId, Pageable pageable);

    Page<AccountEntity> findAllByAccountStatus(AccountStatus status, Pageable pageable);
}
