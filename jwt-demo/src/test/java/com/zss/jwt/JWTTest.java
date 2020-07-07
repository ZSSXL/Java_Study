package com.zss.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.zss.jwt.config.JwtConfig;
import com.zss.jwt.util.MapUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/7 13:05
 * @desc JWT 测试
 */
public class JWTTest extends BaseTest {

    @Autowired
    private JwtConfig jwtConfig;

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZW1vIiwiaXNzIjoiWlNTIiwiZXhwIjoxNTk2NjkzNTAyLCJ1c2VySWQiOiIxMDAxIiwiZW1haWwiOiIxMjM0NTVAMTIzLmNvbSIsInVzZXJuYW1lIjoiWlNTIn0.Jt8GZHeCX-VUEq-Iv22z-QrbkPgS0mUDhTFr2hWRVaA";

    @Test
    public void createTokenTest() {
        String token = jwtConfig.createToken(MapUtil.create(
                "username", "ZSS",
                "userId", "1001",
                "email", "123455@123.com"
        ));
        System.out.println("JWT : [" + token + "]");
    }

    @Test
    public void getClaimTest() {
        Claim username = jwtConfig.getClaim(token, "username");
        System.out.println(username.asString() + " : " + username.toString());
    }

    @Test
    public void isTokenExpireTest() {
        boolean tokenExpired = jwtConfig.isValid(token);
        System.out.println("Is token expire : [" + tokenExpired + "]");
    }
}
