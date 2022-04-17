package com.tams.base.websocket;

import com.tams.base.websocket.model.UserWsModel;
import com.tams.model.ResultModel;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/4/17
 **/

@Service
public class WSService {

    public void reportWsLoginOut(UserWsModel userModel){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(401);
        resultModel.setMsg("账号在他出登陆,被强制退出");
        WebSocketService.sendMessage(userModel,resultModel);
    }

    public void reportWsAutoLoginOut(UserWsModel userModel){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(401);
        resultModel.setMsg("身份信息已过期,被强制退出");
        WebSocketService.sendMessage(userModel,resultModel);
    }

}
