package com.quimbaya.clientes_api.security.domain.filter;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quimbaya.clientes_api.security.aplication.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                DecodedJWT jwt = jwtService.verifyToken(token);

                String username = jwt.getSubject();
                // Autenticación del usuario sin asignar roles
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response); // Solo se llama si la autenticación es exitosa
            } catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.getWriter().write("Token inválido o expirado");
                return; // No continuar con el filtro
            }
        } else {
            filterChain.doFilter(request, response); // Si no hay token, se continúa con el filtro
        }
    }
}
