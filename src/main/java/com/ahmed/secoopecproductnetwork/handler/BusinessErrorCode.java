package com.ahmed.secoopecproductnetwork.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter

public enum BusinessErrorCode {
    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Login and / or Password is incorrect"),
    NOT_FOUND(304, HttpStatus.NOT_FOUND, "USER NOT FOUND"),


    ;
    private final int code;
    private final HttpStatus  HTTPSTATUS;

    private final String description;


    BusinessErrorCode(int code,HttpStatus HTTPSTATUS, String description) {
        this.code = code;
        this.description = description;
        this.HTTPSTATUS = HTTPSTATUS;
    }
}
