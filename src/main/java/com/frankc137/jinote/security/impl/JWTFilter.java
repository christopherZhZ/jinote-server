package com.frankc137.jinote.security.impl;

import com.frankc137.jinote.security.constant.C;
import com.frankc137.jinote.security.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;

public class JWTFilter extends BasicAuthenticationFilter {

    public JWTFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new TokenException("Token is empty!");
        }

        String user = null;
        try {
            user = Jwts.parser().setSigningKey(C.JWT_SIGNING_KEY).parseClaimsJws(token.replace("Bearer ", "")).getBody().getSubject();
            if (user != null) {
                String[] split = user.split("-")[1].split(",");
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    authorities.add(new GrantedAuthorityProxy(split[i]));
                }
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        } catch (ExpiredJwtException e) {
            throw new TokenException("Token Expired");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("Token Wrong Format");
        } catch (MalformedJwtException e) {
            throw new TokenException("Token Not Formed");
//        } catch (SignatureException e) {
//            throw new TokenException("Signature Failed");
        } catch (IllegalArgumentException e) {
            throw new TokenException("Illegal Arguments");
        }
        return null;
    }

}
