package com.afundacionfp.exception;

public class HttpExceptionCode extends Exception{
    private int errorCode;

    public HttpExceptionCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
