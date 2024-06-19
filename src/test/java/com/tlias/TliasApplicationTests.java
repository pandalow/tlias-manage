package com.tlias;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

//@SpringBootTest
class TliasApplicationTests {

//    @Test
//    void testUuid() {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(UUID.randomUUID().toString());
//
//        }
//    }
    @Test
    public void testJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String tlias = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "tlias")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(tlias);
    }

    @Test
    public void parse(){
        Claims tlias = Jwts.parser()
                .setSigningKey("tlias")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxMTk4MjE4OH0.kBUMjt8Nr5OPNBLagbWNV8hSesetNdEjt8i1kU4PQnE")
                .getBody();
        System.out.println(tlias);
    }

}
