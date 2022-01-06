package com.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sms.enums.ResponseCode;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult<T>{

    private  Integer code;

    private  String msg;

    private  T data;


    public AjaxResult (T data){
        this.code = ResponseCode.OK.getCode();
        this.msg  = ResponseCode.OK.getMsg();
        this.data = data;
    }

    public static <T> AjaxResult<T> succ(T data){
        return new AjaxResult<T>(ResponseCode.OK.getCode() , ResponseCode.OK.getMsg(), data);
    }

    public static AjaxResult<Void> succ(String msg){
        return new AjaxResult<>(ResponseCode.OK.getCode() , msg, null);
    }

    public static  AjaxResult<Void> error(Integer code , String msg){
        return new AjaxResult<>(code , msg ,null);
    }

    public static  AjaxResult<Void> error( String msg){
        return new AjaxResult<>(500 , msg ,null);
    }


    public AjaxResult<T> succ(Integer code , String msg , T data){
        this.code = code;
        this.msg = msg;
        return  this;
    }

    public AjaxResult<T> succ(Integer code , T data){
        this.code = code;
        this.msg = "";
        return  this;
    }

    public AjaxResult<T> succ(ResponseCode responseCode , T data){
        this.code = 200;
        this.msg  = responseCode.getMsg();
        this.data = data;
        return this;
    };

    public AjaxResult<T> msg(String msg){
        this.code = 200;
        this.msg = msg;
        return  this;
    }


    public AjaxResult<T> error(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        return this;
    };
}
