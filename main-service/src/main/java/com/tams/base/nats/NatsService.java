package com.tams.base.nats;

import com.nats.tams.annotation.NatsListener;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

@Service
public class NatsService {


    @NatsListener(subject = "report.*.hpu.tams.v1.call.notice.id")
    public void report(Object object){

    }

}
