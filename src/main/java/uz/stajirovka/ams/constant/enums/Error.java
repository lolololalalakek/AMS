package uz.stajirovka.ams.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {
    // --- Общие системные ошибки (10xxx) ---
    INTERNAL_SERVICE_ERROR(10001, "Internal service error"),
    JSON_NOT_VALID(10002, "JSON not valid"),
    VALIDATION_ERROR(10003, "Validation error"),
    INVALID_REQUEST_PARAM(10004, "Invalid request parameter"),
    METHOD_NOT_SUPPORTED(10005, "Method not supported"),
    HANDLER_NOT_FOUND(10006, "Handler not found"),

    // --- Ошибки доступа и авторизации (11xxx) ---
    ACCESS_DENIED(11001, "Access denied"),
    UNAUTHORIZED(11002, "Unauthorized access"),

    // --- Бизнес-ошибки AMS: Аккаунты (20xxx) ---
    ACCOUNT_NOT_FOUND(20001, "Account not found"),
    ACCOUNT_ALREADY_EXISTS(20002, "Account with this number already exists"),
    ACCOUNT_IS_BLOCKED(20003, "Account is blocked or frozen"),
    ACCOUNT_IS_CLOSED(20004, "Account is already closed"),
    INSUFFICIENT_FUNDS(20005, "Insufficient funds on account balance"),
    CURRENCY_MISMATCH(20006, "Operation not allowed: Currency mismatch"),
    DUPLICATE_ACCOUNT_TYPE(20007, "User already has an active account of this type and currency"),

    // --- Бизнес-ошибки AMS: Транзакции (30xxx) ---
    TRANSACTION_NOT_FOUND(30001, "Transaction not found"),
    TRANSACTION_FAILED(30002, "Transaction processing failed"),
    INVALID_AMOUNT(30003, "Invalid transaction amount");

    private final int code;
    private final String message;

}
