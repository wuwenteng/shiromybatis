package com.ssm.test.shiromybatis.handler;

import com.ssm.test.shiromybatis.exception.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ASUS
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(ErrorException.class)
    @ResponseBody
    public ResponseEntity getException(ErrorException e) {
        String message = e.getMessage();
        return ResponseEntity.badRequest().body("error: " + message);
    }
}
