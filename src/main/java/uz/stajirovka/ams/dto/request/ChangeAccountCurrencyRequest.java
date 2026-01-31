package uz.stajirovka.ams.dto.request;

import lombok.Getter;
import lombok.Setter;
import uz.stajirovka.ams.constant.enums.AccountCurrency;

@Getter
@Setter
public class ChangeAccountCurrencyRequest {
    private String accountNumber;
    private AccountCurrency currency;
}
