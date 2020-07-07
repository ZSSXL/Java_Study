package com.zss.jwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/7 11:13
 * @desc JWT configure
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "config.jwt")
public class JwtConfig {

    private String secret;

    private long expire;

    private String header;

    private String issuer;

    private String subject;

    /**
     * 有效期 - 30天
     */
    private static final long EXPIRES_TIME = 1000L * 60L * 60L * 24L * 30L;

    /**
     * 生成Token
     *
     * @param map 自定义键值对
     * @return String
     */
    public String createToken(Map<String, String> map) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_TIME));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue());
        }
        return builder.sign(algorithm);
    }

    /**
     * 通过token获取其中的key对应的值
     *
     * @param token 认证的token
     * @param key   对应的键
     * @return claim对象
     */
    public Claim getClaim(String token, String key) throws JWTVerificationException {
        DecodedJWT jwt = getDecoded(token);
        return jwt.getClaim(key);
    }

    /**
     * 验证Token是否过期失效
     *
     * @param token token
     * @return boolean
     */
    public boolean isValid(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        } else {
            try {
                getDecoded(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * 获取 decoded
     *
     * @param token token串
     * @return DecodedJWT
     */
    private DecodedJWT getDecoded(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

}
