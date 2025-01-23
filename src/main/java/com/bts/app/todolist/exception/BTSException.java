package com.bts.app.todolist.exception;

public class BTSException extends Exception {
    private Integer errorCode;
    private String message;
    private String[] values;

    public BTSException(String message) {
        super(message);
        this.errorCode = 500;
        this.message = message;
    }

    public BTSException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BTSException(Integer errorCode, String message, String... values) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.values = values;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String[] getValues() {
        return this.values;
    }
}