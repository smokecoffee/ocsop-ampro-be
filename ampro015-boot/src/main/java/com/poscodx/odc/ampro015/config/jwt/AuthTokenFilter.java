package com.poscodx.odc.ampro015.config.jwt;

import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscodx.odc.ampro015.config.services.EmployeeDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private ServiceLifecycle serviceLifecycle;

  @Autowired
  private EmployeeDetailsServiceImpl employeeDetailsService;

//  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String jwt = parseJwt(request);
    if(serviceLifecycle.requestLogoutAccessTokenService().findByToken(jwt).isEmpty()){//check token in blacklist
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//          String username = jwtUtils.getUserNameFromJwtToken(jwt);
        String id = jwtUtils.getUserIdFromJwtToken(jwt);

//          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserDetails userDetails = employeeDetailsService.loadUserById(id);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }

    return null;
  }
}
