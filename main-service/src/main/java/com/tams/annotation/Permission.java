package com.tams.annotation;

import com.tams.enums.OperationTypeEnum;
import com.tams.enums.RightTypeEnum;

import java.lang.annotation.*;

/**
 * @author swiChen
 * @date 2022/3/11
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    RightTypeEnum right();

    OperationTypeEnum operation() ;

    boolean isLog() default true;

}
