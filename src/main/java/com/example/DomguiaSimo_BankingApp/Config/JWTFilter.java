package com.example.DomguiaSimo_BankingApp.Config;

import com.example.DomguiaSimo_BankingApp.User.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//@Configuration
@Component
public class JWTFilter extends OncePerRequestFilter{

    @Autowired
    private JWTServices jwtService;

    @Autowired
    private UserService userService;

    List<String> protectedRoutes = List.of(
            "/api/transaction"
    );
    private boolean startsWith(String url){
        for(int i=0;i<protectedRoutes.size();i++){
            if(url.startsWith(protectedRoutes.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // Skip JWT validation for public endpoints
        if (protectedRoutes.contains(requestURI) || startsWith(requestURI)) {

            String authorisation = request.getHeader("Authorization");
            System.out.println(authorisation);
            if( authorisation==null || !authorisation.startsWith("Bearer ")){
                response.sendError(401 ,"Unauthorised");
            }
            else{
                String token = authorisation.substring(7);

                String email = jwtService.extractEmail(token);
                System.out.println(email);
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userService.loadUserByUsername(email);
                    System.out.println(userDetails.getAuthorities());

                    if (jwtService.validateToken(token, userDetails)) {
                        System.out.println("Token validated");
                        System.out.println(jwtService.validateToken(token, userDetails));
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request, response);

            }
        }
        else{
            System.out.println("Not protected");
            filterChain.doFilter(request, response);
        }

    }
}
