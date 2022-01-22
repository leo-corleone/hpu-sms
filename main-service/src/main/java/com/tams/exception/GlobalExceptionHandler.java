package com.tams.exception;

import com.nats.tams.exception.NatsException;
import com.tams.exception.base.BusinessException;
import com.tams.exception.jwt.JWTException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author swiChen
 * @date 2022/1/18
 **/

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NatsException.class)
    public void nastException(NatsException e, HttpServletResponse response){
        setError(e.getCode() , e.getMsg() , response);
    }

    @ExceptionHandler(value = BusinessException.class)
    public void businessException(BusinessException e, HttpServletResponse response){
        setError(e.getCode() , e.getMsg() , response);
    }

    @ExceptionHandler(value = JWTException.class)
    public void jwtException(JWTException e, HttpServletResponse response){
        setError(e.getCode() , e.getMsg() , response);
    }

    private void setError(Integer code , String msg , HttpServletResponse response){
        try {
          response.sendError(code , msg);
          response.setContentType("application/json;charset=UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
