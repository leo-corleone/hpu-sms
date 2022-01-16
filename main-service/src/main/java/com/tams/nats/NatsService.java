package com.tams.nats;

import com.nats.tams.annotation.NatsListener;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

@Service
public class NatsService {

   @NatsListener(subject = "report.io" , log = true)
   public void report(){

   }

   @NatsListener(subject = "report.io.report1" , log = true)
   public void report1(){

   }

}
