package uz.stajirovka.ams.constant;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class Constant {
    public static final BigDecimal INITIAL_BALANCE = BigDecimal.ZERO;

    public static final int ACCOUNT_NUMBER_LENGTH = 20;

    // 20216 - Стандартный префикс для счетов физлиц
    public static final String INDIVIDUAL_ACCOUNT_PREFIX = "20216 ";

    // 20210 - Стандартный префикс для счетов юрлиц (компании)
    public static final String LEGAL_ENTITY_ACCOUNT_PREFIX = "20210";

    //20206 - Валютный счет для физлица
    public static final String INDIVIDUAL_CURRENCY_TRANSIT_PREFIX = "20206";

    //20218 - Валютный счет для юрлица
    public static final String LEGAL_TRANSIT_CURRENCY_PREFIX = "20218";

    // Проверка, что номер счета состоит ровно из 20 цифр
    public static final String ACCOUNT_NUMBER_REGEX = "^\\d{20}$";

    // Проверка формата ИНН (для юрлиц)
    public static final String LEGAL_ENTITY_INN_REGEX = "^\\d{10}$";

    // Форматы
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

}
