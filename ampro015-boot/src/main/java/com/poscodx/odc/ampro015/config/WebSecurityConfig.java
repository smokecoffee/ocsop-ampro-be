package com.poscodx.odc.ampro015.config;

import com.poscodx.odc.ampro015.config.exceptionhandler.AuthEntryPoint;
import com.poscodx.odc.ampro015.config.jwt.AuthTokenFilter;
import com.poscodx.odc.ampro015.config.provider.CustomAuthenticationProvider;
import com.poscodx.odc.ampro015.config.services.EmployeeDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthTokenFilter authTokenFilter;
    @Autowired
    private EmployeeDetailsServiceImpl employeeDetailsService;
    @Autowired
    private AuthEntryPoint unauthorizedHandler;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    // Details omitted for brevity
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider)
                .userDetailsService(employeeDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();

    // Set session management to stateless
    http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

    // Set unauthorized requests exception handler
    http = http
            .exceptionHandling()
            .authenticationEntryPoint(
                    unauthorizedHandler
            )
            .and();

    // Set permissions on endpoints
    http.authorizeRequests()
            // Our public endpoints
            .antMatchers(HttpMethod.POST, "/author/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/author/signin").permitAll()
            .antMatchers(HttpMethod.POST, "/author/logout").permitAll()
            .antMatchers(HttpMethod.GET, "/images/**").permitAll()
            .antMatchers(HttpMethod.GET, "/user-management/**").permitAll()

            .antMatchers(HttpMethod.GET, "**/dashboard.do").permitAll()
            .antMatchers(HttpMethod.POST, "**/dashboard.do").permitAll()
            .antMatchers(HttpMethod.GET, "**/dashboard.do/**").permitAll()
            .antMatchers(HttpMethod.POST, "**/dashboard.do/**").permitAll()

            .antMatchers(HttpMethod.GET, "**/asset.do").permitAll()
            .antMatchers(HttpMethod.POST, "**/asset.do").permitAll()
            .antMatchers(HttpMethod.GET, "**/booking.do").permitAll()
            .antMatchers(HttpMethod.POST, "**/booking.do").permitAll()
            .antMatchers(HttpMethod.GET, "**/project-monitoring.do").permitAll()
            .antMatchers(HttpMethod.POST, "**/project-monitoring.do").permitAll()
            .antMatchers(HttpMethod.GET, "**/task.do").permitAll()
            .antMatchers(HttpMethod.POST, "**/task.do").permitAll()
            .antMatchers(HttpMethod.GET, "**/projects.do").permitAll()
            .antMatchers(HttpMethod.POST, "**/projects.do").permitAll()



            .anyRequest().permitAll();
            //.anyRequest().authenticated();
    // Add JWT token filter
    http.addFilterBefore(
            authTokenFilter,
            UsernamePasswordAuthenticationFilter.class
    );

    }
}
