package com.poscodx.odc.ampro015.config.provider;

import com.poscodx.odc.ampro015.config.services.EmployeeDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final EmployeeDetailsServiceImpl employeeDetailsService;
    private final PasswordEncoder encoder;
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String id = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserDetails currentUser = employeeDetailsService.loadUserById(id);
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Example: validating credentials
        if (!encoder.matches(password, currentUser.getPassword())) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        // Create a fully authenticated Authentication object
        return new UsernamePasswordAuthenticationToken(currentUser, password, currentUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
