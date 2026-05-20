package com.quimbaya.clientes_api.security.application.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.quimbaya.clientes_api.security.domain.exception.InvalidTokenException;
import com.quimbaya.clientes_api.user.domain.model.User;

@Service
public class JwtService {

    private final String secret;
    private final long expirationMillis;
    private final String issuer;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationMillis,
            @Value("${jwt.issuer}") String issuer) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
        this.issuer = issuer;
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expirationMillis);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.userEmail())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }

    public String getSubjectFromToken(String token) {
        return verifyToken(token).getSubject();
    }
}
