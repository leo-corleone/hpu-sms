package com.tams.base.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tams.exception.jwt.JWTException;
import com.tams.model.LoginModel;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author swiChen
 * @date 2022/1/21
 **/

@Service
public class JWTService {

  private static final String SECRET = "sWichEnIsKing";

  private static final Integer TIME = 20;

  public String createToken(LoginModel login){

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE , TIME);

   return  JWT.create()
               .withClaim("uid", login.getUsername())
               .withClaim("role" , login.getRole())
               .withExpiresAt(calendar.getTime())
               .sign(Algorithm.HMAC512(SECRET));

  }

  private void verify(String token){
      try {
          JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token);
      } catch (Exception e){
          throw new JWTException("身份校验失败");
      }

  }

  private LoginModel verifyGetLoginBody(String token){
      try {
          DecodedJWT verify = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token);
          String uid = verify.getClaim("uid").asString();
          String role = verify.getClaim("role").asString();
          LoginModel loginBody = new LoginModel();
          loginBody.setUsername(uid);
          loginBody.setRole(role);
          return loginBody;
      } catch (Exception e){
          throw new JWTException("身份校验失败");
      }
  }

}
