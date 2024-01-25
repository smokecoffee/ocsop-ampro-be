package com.poscodx.odc.ampro015.config.services;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.poscdx.odc.ampro015.domain.utils.ConstantUtil;
import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class EmployeeDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String avatar;

    private String email;

    private List<String> role;

  private List<Map<Integer, String>> listPermission;

  @JsonIgnore
  private String password;


  private Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetailsImpl(String id, String username, String email, String avatar, String password, List<String> role,
                               Collection<? extends GrantedAuthority> authorities, List<Map<Integer, String>> listPermission) {
        this.id = id;
        this.username = username;
        this.email = email;
        //this.avatar = avatar;
        this.avatar = ConstantUtil.applyEmployeeAvatarPath(avatar, "Employee/");
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.listPermission = listPermission;
    }

    public static EmployeeDetailsImpl build(M00EmployeeJpo user, List<String> listRoles, List<Map<Integer, String>> listPermission) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Map<Integer, String> map : listPermission) {
            Set set = map.keySet();
            for (Object key : set) {
                authorities.add(new SimpleGrantedAuthority(map.get(key)));
            }
        }
        //Collections.singleton(new SimpleGrantedAuthority(roleName));

        return new EmployeeDetailsImpl(
                user.getEmpId(),
                user.getName(),
                user.getMail(),
                user.getAvatar(),
                user.getPassword(),
                listRoles,
                authorities,
                listPermission);
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
