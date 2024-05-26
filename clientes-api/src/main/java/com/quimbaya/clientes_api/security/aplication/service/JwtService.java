package com.quimbaya.clientes_api.security.aplication.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.quimbaya.clientes_api.security.domain.exception.InvalidCredentialsException;
import com.quimbaya.clientes_api.user.domain.model.Users;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.issuer}")
    private String issuer;
    

    public String generateToken(Users user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        System.out.println(expiryDate);
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(user.getUserEmail())
            .withIssuedAt(now)
            .withExpiresAt(expiryDate)
            .sign(Algorithm.HMAC256(secret));
    }

    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
        	 
            throw new InvalidCredentialsException();
        }
    }

    public String getSubjectFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    } 
    
}

