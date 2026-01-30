package uz.stajirovka.ams.service;

import org.springframework.stereotype.Component;
import uz.stajirovka.ams.constant.Constant;
import uz.stajirovka.ams.constant.enums.AccountCurrency;
import uz.stajirovka.ams.constant.enums.AccountType;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class AccountHelper {
    private final Random random = new SecureRandom();

    public String generateAccountNumber(AccountType type, AccountCurrency currency) {
        String prefix = determinePrefix(type, currency);
        String currencyCode = currency.getCode();

        StringBuilder sb = new StringBuilder(prefix);
        sb.append(currencyCode);

        int randomPartLength = Constant.ACCOUNT_NUMBER_LENGTH - sb.length();

        for (int i = 0; i < randomPartLength; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    private String determinePrefix(AccountType type, AccountCurrency currency) {
        // Проверяем, валютный ли счет (не UZS)
        boolean isCurrency = !currency.equals(AccountCurrency.UZS);

        if (type == AccountType.INDIVIDUAL) {
            return isCurrency
                    ? Constant.INDIVIDUAL_CURRENCY_TRANSIT_PREFIX
                    : Constant.INDIVIDUAL_ACCOUNT_PREFIX;
        } else {
            return isCurrency
                    ? Constant.LEGAL_TRANSIT_CURRENCY_PREFIX
                    : Constant.LEGAL_ENTITY_ACCOUNT_PREFIX;
        }
    }
}
