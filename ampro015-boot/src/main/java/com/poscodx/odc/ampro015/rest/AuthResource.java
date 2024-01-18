package com.poscodx.odc.ampro015.rest;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;
import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.payload.request.LoginRequest;
import com.poscdx.odc.ampro015.domain.entity.payload.response.JwtResponse;
import com.poscdx.odc.ampro015.domain.entity.payload.response.LoginUserInfo;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscodx.odc.ampro015.config.jwt.JwtUtils;
import com.poscodx.odc.ampro015.config.services.EmployeeDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "${cross.origins}")
@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthResource {

  private final AuthenticationManager authenticationManager;
  private final ServiceLifecycle serviceLifecycle;
  private final PasswordEncoder encoder;
  private final JwtUtils jwtUtils;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody M00Employee signUpRequest) {
    return (ResponseEntity<?>) ResponseEntity.ok();
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getId(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    //EmployeeDetailsImpl userDetails1 = (EmployeeDetailsImpl) authentication.getPrincipal();
    EmployeeDetailsImpl userDetails = (EmployeeDetailsImpl) authentication.getPrincipal();

    List<String> permissions = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    LoginUserInfo userInfo = LoginUserInfo.builder()
            .id(userDetails.getId())
            .username(userDetails.getUsername())
            .email(userDetails.getEmail())
            .roleId(userDetails.getRole())
            .avatar(userDetails.getAvatar())
            .build();

    return ResponseEntity.ok(new JwtResponse(jwt,
            userInfo,
            permissions));
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    String jwtToken = parseJwt(request);
    System.out.println("jwt token: " + jwtToken);
    if(jwtToken == null || jwtToken.isEmpty()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in yet!");
    } else {
      if(serviceLifecycle.requestExcanAccessTokenService().findByToken(jwtToken).isEmpty()){
        //Save logout access token to blacklist
        LogoutAccessToken token = LogoutAccessToken.builder()
                .token(jwtToken)
                .status(1) //blacklist token
                .build();
        serviceLifecycle.requestExcanAccessTokenService().register(token);
      }
      return ResponseEntity.ok().body("Logout successfully!");
    }
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    System.out.println("Authorization: " + headerAuth);

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }

    return null;
  }
}
