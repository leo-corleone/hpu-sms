package com.tams.exception;

import com.nats.tams.exception.NatsException;
import com.tams.exception.base.BusinessException;
import org.springframework.http.HttpStatus;
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

        try {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMsg());
            response.setContentType("application/json;charset=UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @ExceptionHandler(value = BusinessException.class)
    public void businessException(BusinessException e, HttpServletResponse response){
        try {
            response.sendError(e.getCode() , e.getMsg());
            response.setContentType("application/json;charset=UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
