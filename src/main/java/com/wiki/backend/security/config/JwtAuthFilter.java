package com.wiki.backend.security.config;

import com.wiki.backend.security.token.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserAuthService userAuthService;

    public JwtAuthFilter(JwtService jwtService, UserAuthService userAuthService) {
        this.jwtService = jwtService;
        this.userAuthService = userAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValidToken = jwtService.tokenValid(token);

            if (isValidToken){
                String lgUser = jwtService.getIsLoginUser(token);
                UserDetails user = userAuthService.loadUserByUsername(lgUser);

                UsernamePasswordAuthenticationToken userDetails = new
                        UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                userDetails.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userDetails);
            }
        }

        filterChain.doFilter(request, response);
    }
}
