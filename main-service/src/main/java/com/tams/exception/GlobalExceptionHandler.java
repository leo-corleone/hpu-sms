package com.tams.exception;

import com.nats.tams.exception.NatsException;
import com.tams.dto.AjaxResult;
import com.tams.exception.base.BusinessException;
import com.tams.exception.jwt.JWTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author swiChen
 * @date 2022/1/18
 **/

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NatsException.class)
    public AjaxResult<Void> nastException(NatsException e, HttpServletResponse response){
        setError(e.getCode() , e.getMsg() , response);
        return AjaxResult.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = JWTException.class)
    public AjaxResult<Void> jwtException(JWTException e, HttpServletResponse response){

        setError(e.getCode() , e.getMsg() , response);
        return AjaxResult.error(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = BusinessException.class)
    public AjaxResult<Void> businessException(BusinessException e, HttpServletResponse response){
        setError(e.getCode() , e.getMsg() , response);
        return AjaxResult.error(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(value = Exception.class)
    public AjaxResult<Void> exception(Exception e, HttpServletResponse response){
        setError(501 , e.getMessage() , response);
        return AjaxResult.error(501,e.getMessage());
    }


    private void setError(Integer code , String msg , HttpServletResponse response){
        log.error("状态码: [{}] msg:[{}]," , code , msg);
        try {
          response.sendError(code , msg);
          response.setContentType("application/json;charset=UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
