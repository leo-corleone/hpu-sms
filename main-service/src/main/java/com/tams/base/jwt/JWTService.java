package com.tams.base.jwt;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tams.base.jwt.util.JWTConstant;
import com.tams.enums.ResponseCode;
import com.tams.exception.jwt.JWTException;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Service
public class JWTService {



  public String createToken(String sysUser){

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR , JWTConstant.TIME);

    return  JWT.create()
               .withClaim(JWTConstant.CLAIM, sysUser)
               .withExpiresAt(calendar.getTime())
               .sign(Algorithm.HMAC512(JWTConstant.SECRET));
  }

  public DecodedJWT verify(String token){
      if (ObjectUtil.isEmpty(token) || "".equals(token.trim())){
          throw new JWTException("用户身份信息有误" , ResponseCode.UnAuth.code);
      }
      try {
        return  JWT.require(Algorithm.HMAC512(JWTConstant.SECRET)).build().verify(token);
      } catch (Exception e){
          throw new JWTException("身份校验失败" , ResponseCode.UnAuth.code);
      }

  }

  public String verifyGetSysUser(String token){

      try {
          DecodedJWT verify = verify(token);
          String sysUser = verify.getClaim(JWTConstant.CLAIM).asString();
          return sysUser;
      } catch (Exception e){
          throw new JWTException("身份校验失败, 请重新登陆" , ResponseCode.UnAuth.code);
      }
  }

}
