package com.sundaohan.server.config.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.config.security
 * @Title JwtTokenUtils
 * @Description TODO
 * @Date 2021/7/23 下午11:25
 */
@Component
public class JwtTokenUtils {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @Title generateToken
     * @Description 根据用户信息生成token
     * @Author sundaohan
     * @Params [userDetails]
     * @return java.lang.String
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * @Title getUserNameFromToken
     * @Description 从token中获取用户名
     * @Author sundaohan
     * @Params [token]
     * @return java.lang.String
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }
    /**
     * @Title validateToken
     * @Description 验证token是否有效
     * @Author sundaohan
     * @Params [token, userDetails]
     * @return boolean
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        //比较UserDetails里的username和token中username是否一致以及token是否失效
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    
    /**
     * @Title canRefresh
     * @Description 判断token是否可以被刷新
     * @Author sundaohan
     * @Params [token]
     * @return boolean
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * @Title refreshToken
     * @Description 刷新token
     * @Author sundaohan
     * @Params [token]
     * @return java.lang.String
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,  new Date());
        return generateToken(claims);
    }

    /**
     * @Title IsTokenExpired
     * @Description 判断token是否失效
     * @Author sundaohan
     * @Params [token]
     * @return boolean
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }
    /**
     * @Title getExpiredDateFromToken
     * @Description 从token中获取过期时间
     * @Author sundaohan
     * @Params [token]
     * @return java.util.Date
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * @Title getClaimsFromToken
     * @Description 从token中获取荷载
     * @Author sundaohan
     * @Params [token]
     * @return io.jsonwebtoken.Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * @Title generateToken
     * @Description 根据荷载生成JWT TOKEN
     * @Author sundaohan
     * @Params [claims]
     * @return java.lang.String
     */
    private String generateToken(Map<String, Object> claims){
        System.out.println(secret);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }
    /**
     * @Title generateExpirationDate
     * @Description 生成token失效时间
     * @Author sundaohan
     * @Params []
     * @return java.util.Date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
