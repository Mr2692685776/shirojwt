package com.newheart.shirojwt.shirojwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * jwt工具类
 * @author hanjie
 * @date 2019/12/30 17:07
 */
public class JwtUtil {

    /**
     * token的过期时间
     */
    public static  final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * 密匙
     */
    public static final String SECRET = "newheart";

    /**
     * 生成Token
     * @param username
     * @return
     */
    public static String sign(String username){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create().withClaim("username",username).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 校验token
     */
    public static boolean verify(String token,String username){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static String getUsername(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (Exception e){
            return null;
        }
    }

}
