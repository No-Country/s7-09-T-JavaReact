package com.tripMate.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenUtil {


    @Value("${ACCESS_TOKEN_VALIDIT_SECONDS}")
    private static Long ttlMillis;;

    @Value("${ACCESS_TOKEN_SECRET}")
    private static String key;;

    @Value("${SECURITY_JWT_ISSUER}")
    private String issuer;


    private final Logger log = LoggerFactory.getLogger(TokenUtil .class);



    public String createToken(UserDetails userDetails) {

        return createToken(new HashMap<>(), userDetails);
    }


    public String createToken(Map<String, Object> extraClaims,
                              UserDetails userDetails) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setClaims(extraClaims)
                .setIssuedAt(now)
                .setSubject(userDetails.getUsername())
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }


    public String extractUserName(String token) {

        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {

        return extractClaims(token, Claims::getExpiration);
    }



    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Key getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);

    }


    public boolean verifyToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token){

        Date exp = extractClaims(token, Claims::getExpiration);
        return exp.before(new Date());
    }
}
