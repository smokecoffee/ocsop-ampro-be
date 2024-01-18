package com.poscodx.odc.ampro015.config.jwt;

import com.poscodx.odc.ampro015.config.services.EmployeeDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

  @Value("${bezkoder.app.jwtSecret}")
  private String jwtSecret;

  @Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    EmployeeDetailsImpl userPrincipal = (EmployeeDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
//        .setSubject((userPrincipal.getUsername()))
        .setSubject((userPrincipal.getId()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();
  }
  
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  public String getUserIdFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
            .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      System.out.println("Invalid JWT token: {}" + e.getMessage());
    } catch (ExpiredJwtException e) {
      System.out.println("JWT token is expired: {}" + e.getMessage());
    } catch (UnsupportedJwtException e) {
      System.out.println("JWT token is unsupported: {}" + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println("JWT claims string is empty: {}" +  e.getMessage());
    }

    return false;
  }
}
