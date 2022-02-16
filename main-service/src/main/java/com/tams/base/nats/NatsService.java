package com.tams.base.nats;

import cn.hutool.core.util.ObjectUtil;
import com.nats.tams.core.NatsClient;
import com.tams.base.nats.model.LoginNatsModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author swiChen
 * @date 2022/1/15
 **/

@Service
@Slf4j
public class NatsService {

   @Resource
   private NatsClient natsClient;

   public  void LoginNats(String subject, LoginNatsModel model){
      if (ObjectUtil.isEmpty(model)){
         model = new LoginNatsModel().setMsg("账户已在其它地方登陆, 请重新登陆").setTime(LocalDateTime.now());
      }
      String topic = String.format("report.hpu.tams.v1.login.%s",subject);
      log.info("nats publish [{}]", topic);
      report(topic , model);
   }

   private void report(String subject , Object data){
       natsClient.publish(subject , data);
   }


}
