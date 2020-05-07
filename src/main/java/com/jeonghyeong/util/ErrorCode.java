package com.jeonghyeong.util;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	// Controller Error
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST , "C01", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "C02", " Invalid Input Value"),

    //MYSQL Error
    DB_NO_RESPONSE(HttpStatus.INTERNAL_SERVER_ERROR, "D01", "internal server error"),

    
    
    //Authentication Error
    INVALID_HEADER(HttpStatus.UNAUTHORIZED, "A01", "Invalid Header"),
    TOKEN_EXPIRE(HttpStatus.FORBIDDEN, "A02", "Token is expired"),
    INVALID_CREDENTIAL(HttpStatus.UNAUTHORIZED,"A03", "Invalid Credentials"),
    CLIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "A04", "Invalid clientId")

    ;
    private final String code;
    private final String message;
    private HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
