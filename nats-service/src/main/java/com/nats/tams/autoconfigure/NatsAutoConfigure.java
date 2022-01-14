package com.nats.tams.autoconfigure;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * @author swiChen
 * @date 2022/1/14
 **/

@ConditionalOnClass(NatsAutoConfigure.class)
@EnableAutoConfiguration
public class NatsAutoConfigure {
}
