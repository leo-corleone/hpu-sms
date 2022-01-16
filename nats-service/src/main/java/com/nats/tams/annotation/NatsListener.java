package com.nats.tams.annotation;

import java.lang.annotation.*;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NatsListener {

    String subject() ;

    String queue()  default "";

    boolean log()   default false;

}
