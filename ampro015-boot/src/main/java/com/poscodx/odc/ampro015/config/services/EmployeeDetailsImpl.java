package com.poscodx.odc.ampro015.config.services;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;



import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class
EmployeeDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private String id;

  private String username;

  private String avatar;

  private String email;

  private String role;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public EmployeeDetailsImpl(String id, String username, String email, String avatar, String password, String role,
                             Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    //this.avatar = avatar;
    this.avatar = "http://172.25.219.61:8080/img/" + avatar;
    this.password = password;
    this.role = role;
    this.authorities = authorities;
  }

  public static EmployeeDetailsImpl build(M00EmployeeJpo user, Set<String> perNames) {

    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    for (String per : perNames){
      authorities.add(new SimpleGrantedAuthority(per));
    }
//    Collections.singleton(new SimpleGrantedAuthority(roleName));

    return new EmployeeDetailsImpl(
        user.getEmpId(),
        user.getName(),
        user.getMail(),
        user.getAvatar(),
        user.getPassword(),
        user.getRole(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    EmployeeDetailsImpl user = (EmployeeDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
