package com.tams.base.jwt;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tams.base.jwt.util.JWTConstant;
import com.tams.enums.RoleEnum;
import com.tams.exception.jwt.JWTException;
import com.tams.model.LoginModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Service
public class JWTService {



  public String createToken(LoginModel login){

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE , JWTConstant.TIME);

   return  JWT.create()
               .withClaim(JWTConstant.USERNAME, login.getUsername())
               .withClaim(JWTConstant.ROLE, login.getRole().getRole())
               .withExpiresAt(calendar.getTime())
               .sign(Algorithm.HMAC512(JWTConstant.SECRET));

  }

  public DecodedJWT verify(String token){
      if (ObjectUtil.isEmpty(token) || "".equals(token.trim())){
          throw new JWTException("用户身份信息有误" , 501);
      }
      try {
        return  JWT.require(Algorithm.HMAC512(JWTConstant.SECRET)).build().verify(token);
      } catch (Exception e){
          throw new JWTException("身份校验失败");
      }

  }

  public LoginModel verifyGetLoginBody(String token){

      try {
          DecodedJWT verify = verify(token);
          String uid = verify.getClaim(JWTConstant.USERNAME).asString();
          String role = verify.getClaim(JWTConstant.ROLE).asString();
          LoginModel loginBody = new LoginModel();
          loginBody.setUsername(uid);
          loginBody.setRole(RoleEnum.StringParseRole(role));
          return loginBody;
      } catch (Exception e){
          throw new JWTException("身份校验失败, 请重新登陆" , HttpStatus.UNAUTHORIZED.value());
      }
  }

}
