package com.felixweb.projeto.resources.exceptions;

public class DataIngretyViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public DataIngretyViolationException(String msg) {
        super (msg);
    }

    public DataIngretyViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
