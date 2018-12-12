package com.ssm.test.shiromybatis.exception;

/**
 * @author ASUS
 */
public class ErrorException extends Exception {
    private String code;
    private String msg;
    public ErrorException() {}

    public ErrorException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
