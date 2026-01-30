package uz.stajirovka.ams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uz.stajirovka.ams.constant.Constant;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountStatus;
import uz.stajirovka.ams.constant.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id; // Технический ID для API (вместо Long)

    @Column(nullable = false)
    Long userId;

    @Pattern(regexp = Constant.ACCOUNT_NUMBER_REGEX)
    @Column(nullable = false, unique = true, length = 20)
    String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AccountCurrency accountCurrency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AccountType accountType;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
