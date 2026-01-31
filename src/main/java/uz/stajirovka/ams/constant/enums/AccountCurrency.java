package uz.stajirovka.ams.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountCurrency {
    USD("840"),
    RUB("810"),
    UZS("000");

    private final String code;
}
