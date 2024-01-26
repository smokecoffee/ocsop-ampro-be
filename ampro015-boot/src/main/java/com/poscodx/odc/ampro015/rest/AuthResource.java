package com.poscodx.odc.ampro015.rest;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;
import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.entity.Pme00PasswordToken;
import com.poscdx.odc.ampro015.domain.entity.payload.request.ForgotPasswordRequest;
import com.poscdx.odc.ampro015.domain.entity.payload.request.LoginRequest;
import com.poscdx.odc.ampro015.domain.entity.payload.request.ResetPasswordRequest;
import com.poscdx.odc.ampro015.domain.entity.payload.response.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscodx.odc.ampro015.MailConfig;
import com.poscodx.odc.ampro015.config.jwt.JwtUtils;
import com.poscodx.odc.ampro015.config.services.EmployeeDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
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
    private final MailConfig mailConfig;

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

   List<String> permissions1 = userDetails.getAuthorities().stream()
           .map(GrantedAuthority::getAuthority)
           .collect(Collectors.toList());

        LoginUserInfo userInfo = LoginUserInfo.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .listRole(userDetails.getRole())
                .avatar(userDetails.getAvatar())
                .build();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userInfo,
                userDetails.getListPermission()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String jwtToken = parseJwt(request);
        System.out.println("jwt token: " + jwtToken);
        if (jwtToken == null || jwtToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in yet!");
        } else {
            if (serviceLifecycle.requestLogoutAccessTokenService().findByToken(jwtToken).isEmpty()) {
                //Save logout access token to blacklist
                LogoutAccessToken token = LogoutAccessToken.builder()
                        .token(jwtToken)
                        .status(1) //blacklist token
                        .build();
                serviceLifecycle.requestLogoutAccessTokenService().register(token);
            }
            return ResponseEntity.ok().body("Logout successfully!");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> ForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        M00Employee employee = serviceLifecycle.requestM00EmployeeService().getEmployeeByEmail(forgotPasswordRequest.getEmail());
        if(employee == null){
            ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
            forgotPasswordResponse.setError(true);
            forgotPasswordResponse.setMessage("Email is not exists on system. Please check again!");
            return ResponseEntity.ok(forgotPasswordResponse);
        }else{
            UUID uuid = UUID. randomUUID();
            Pme00PasswordToken pme00PasswordToken = getPme00PasswordToken(employee, uuid);
            Pme00PasswordToken savePme00PasswordToken = serviceLifecycle.requestPasswordService().register(pme00PasswordToken);
            //TODO: send email with build info: need to service send email provider
            String resetHtmlTemplate = LoadTemplate();
            ConstantUtil.MAIL_SMTP_SERVER = mailConfig.getSmtpHostServer();
            ConstantUtil.MAIL_SMTP_SERVER_PORT = mailConfig.getSmtpHostServerPort();
            ConstantUtil.MAIL_SMTP_EMAIL_ID = mailConfig.getEmailId();
            ConstantUtil.MAIL_SMTP_EMAIL_ID_ALIAS = mailConfig.getEmailName();
            ConstantUtil.MAIL_SMTP_EMAIL_USERNAME = mailConfig.getUserName();
            ConstantUtil.MAIL_SMTP_EMAIL_PASSWORD = mailConfig.getPassword();
            ConstantUtil.MAIL_FRONT_END_URL = mailConfig.getFrontEndUrl();

            Map<String, String> map = new HashMap<String, String>();
            map.put("url", mailConfig.getFrontEndUrl());
            map.put("token",uuid.toString());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                resetHtmlTemplate = resetHtmlTemplate.replace("${" + entry.getKey() + "}", entry.getValue());
            }

            serviceLifecycle.requestLevel2Service().sendMail(employee.getMail(),mailConfig.getSubject(),resetHtmlTemplate);

            ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
            forgotPasswordResponse.setError(false);
            forgotPasswordResponse.setMessage("Please check email to process next step");
            return ResponseEntity.ok(forgotPasswordResponse);
        }
    }
    @CrossOrigin
    @GetMapping("/reset-password/{token}")
    public ResponseEntity<?> ResetPasswordGetToken(@PathVariable("token") String token) {
        //String s = LoadTemplate();

        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        Pme00PasswordToken pme00PasswordToken = serviceLifecycle.requestPasswordService().FindPasswordTokenByToken(token);
        if(pme00PasswordToken==null){
            resetPasswordResponse.setError(true);
            resetPasswordResponse.setMessage("Token is not exists on system. Please check again!");
            return ResponseEntity.ok(resetPasswordResponse);
        }else {
            if(pme00PasswordToken.IsExpired()){
                resetPasswordResponse.setError(true);
                resetPasswordResponse.setMessage("Token is expired!");
            }else{
                resetPasswordResponse.setError(false);
                resetPasswordResponse.setMessage("");
                resetPasswordResponse.setEmpId(pme00PasswordToken.getEmpId());
            }
            return ResponseEntity.ok(resetPasswordResponse);
        }
    }
    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> ResetPassword(@Valid @PathVariable("token") String token,
                                           @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        Pme00PasswordToken pme00PasswordToken = serviceLifecycle.requestPasswordService().FindPasswordTokenByToken(token);

        M00Employee m00Employee = serviceLifecycle.requestM00EmployeeService().find(pme00PasswordToken.getEmpId());
        if (m00Employee == null) {
            resetPasswordResponse.setError(true);
            resetPasswordResponse.setMessage("Token is not exists on system. Please check again!");
            return ResponseEntity.ok(resetPasswordResponse);
        } else {
            if (pme00PasswordToken.IsExpired()) {
                resetPasswordResponse.setError(true);
                resetPasswordResponse.setMessage("Token is expired!");
            } else {
                if(!resetPasswordRequest.getPassword().equals(resetPasswordRequest.getConfirmPassword())){
                    resetPasswordResponse.setError(true);
                    resetPasswordResponse.setMessage("Password is not match!");
                }else {
                    String passwordToMd5Hex = DigestUtils
                            .md5Hex(resetPasswordRequest.getPassword());
                    m00Employee.setPassword(passwordToMd5Hex);
                    M00Employee updateM00Employee = serviceLifecycle.requestM00EmployeeService().modify(m00Employee);

                    resetPasswordResponse.setError(false);
                    resetPasswordResponse.setMessage("Password is changed! You can login again, now!");
                }
            }
            return ResponseEntity.ok(resetPasswordResponse);

        }

    }

    @NotNull
    private static Pme00PasswordToken getPme00PasswordToken(M00Employee employee, UUID uuid) {
        Pme00PasswordToken pme00PasswordToken=new Pme00PasswordToken();
        pme00PasswordToken.setEmpId(employee.getEmpId());
        pme00PasswordToken.setToken(uuid.toString());
        pme00PasswordToken.setCreateAt(new Date());
        pme00PasswordToken.setCreateBy("0");
        pme00PasswordToken.setUpdateAt(new Date());
        pme00PasswordToken.setUpdateBy("0");
        long currentTimeInMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeInMillis);
        calendar.add(Calendar.HOUR, ConstantUtil.MAX_EXPIRE_TOKEN);
        pme00PasswordToken.setExpire(calendar.getTimeInMillis());
        return pme00PasswordToken;
    }

    private String LoadTemplate() {
        try {
            return new String(Files.readAllBytes(ResourceUtils.getFile("classpath:password-reset.html").toPath()));
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "";
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
