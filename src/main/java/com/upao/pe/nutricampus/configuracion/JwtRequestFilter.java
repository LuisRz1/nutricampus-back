package com.upao.pe.nutricampus.configuracion;

import com.upao.pe.nutricampus.servicios.JwtUserDetailsServicio;
import com.upao.pe.nutricampus.util.EncryptionUtil;
import com.upao.pe.nutricampus.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    JwtUserDetailsServicio jwtUserDetailsServicio;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String tokenFromRequest = request.getHeader("Authorization");
        String userName=null;
        String encryptedJwtToken=null;
        String jwtToken=null;
        logger.debug("Inside JwtRequestFilter--OncePerRequestFilter");
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (tokenFromRequest != null && tokenFromRequest.startsWith("Bearer ")) {
            encryptedJwtToken = tokenFromRequest.substring(7);
            jwtToken = EncryptionUtil.decrypt(encryptedJwtToken);
            try {
                userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("No tiene permitido obtener JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token ha expirado");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        //  Once we get the token validate it and extract username(principal/subject) from it.
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsServicio.loadUserByUsername(userName);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
