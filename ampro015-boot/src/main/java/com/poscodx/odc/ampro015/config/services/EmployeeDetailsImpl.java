package com.poscodx.odc.ampro015.config.services;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.poscdx.odc.ampro015.domain.utils.Utils;
import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;

import lombok.Data;
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

    private Map <String, List<String>> permissionMap;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetailsImpl(String id, String username, String email, String avatar, String password, List<String> role,
                               Collection<? extends GrantedAuthority> authorities, Map <String, List<String>> permissionMap) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatar = Utils.applyEmployeeAvatarPath(avatar, "Employee");
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.permissionMap = permissionMap;
    }

    public static EmployeeDetailsImpl build(M00EmployeeJpo user, List<String> listRoles, List<Map<String, String>> listPermission) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Map <String, List<String>> permissionMap = new HashMap<>();
        List<String> permissionList;
        for (Map<String, String> map : listPermission) {
            Set<String> set = map.keySet();
            for (String key : set) {
                authorities.add(new SimpleGrantedAuthority(map.get(key)));
                permissionList = permissionMap.get(key) == null ? new ArrayList<>() : permissionMap.get(key);
                if (!permissionList.contains(map.get(key))) permissionList.add(map.get(key));
                permissionMap.put(key, permissionList);
            }
        }

        return new EmployeeDetailsImpl(
                user.getEmpId(),
                user.getName(),
                user.getMail(),
                user.getAvatar(),
                user.getPassword(),
                listRoles,
                authorities,
                permissionMap);
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
