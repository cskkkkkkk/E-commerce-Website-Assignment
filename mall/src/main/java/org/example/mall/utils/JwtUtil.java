package org.example.mall.utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName JwtUtil工具类
 * @project agriBlissMart_common
 * @Description
 * @Version 1.0
 */
@Slf4j
public class JwtUtil {

    /**
     * 生成jwt
     * 使用Hs256算法，私钥使用固定密钥
     * @param secretKey  jwt密钥
     * @param ttlMillis  jwt过期时间，单位毫秒
     * @param claims     设置的信息
     * @return
     */
    public static String generateToken(String secretKey, long ttlMillis, Map<String, Object> claims){
        //指定加密算法
        SecureDigestAlgorithm<SecretKey, SecretKey> algorithm = Jwts.SIG.HS256;
        //生成JWT的时间
        long expMillis = System.currentTimeMillis()+ttlMillis;
        Date exp = new Date(expMillis);
        //密钥实例
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String compact = Jwts.builder()
                .signWith(key, algorithm) //设置签名使用的签名算法和签名使用的秘钥
                //如果有私有声明，一点要先设置这个自己创建的私有的声明，这个是给builder的claims赋值，一旦卸载标准的声明赋值之后，就是覆盖了那些标准的声明的
                .expiration(exp)
                .claims(claims) //设置自定义负载信息
                .compact();//设置过期时间
        return compact;
    }


    /**
     * 解析jwt
     * @param token
     * @param secretKey
     * @return
     */
    public static Jws<Claims> parserToken(String token, String secretKey){
        //密钥实例
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)  //设置签名的密钥
                .build()
                .parseSignedClaims(token); //设置要解析的jwt
        return claimsJws;
    }

}

