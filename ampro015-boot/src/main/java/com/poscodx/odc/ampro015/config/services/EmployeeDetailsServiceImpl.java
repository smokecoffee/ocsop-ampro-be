package com.poscodx.odc.ampro015.config.services;

import com.poscodx.odc.ampro015.store.jpo.ExcanPerRoleJpo;
import com.poscodx.odc.ampro015.store.jpo.ExcanRoleJpo;

import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;


import com.poscodx.odc.ampro015.store.repository.ExcanPerRoleRepository;
import com.poscodx.odc.ampro015.store.repository.ExcanPermissionRepository;
import com.poscodx.odc.ampro015.store.repository.ExcanRoleRepository;

import com.poscodx.odc.ampro015.store.repository.M00EmployeeRepository;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {
  @Autowired
  M00EmployeeRepository employeeRepository;

  @Autowired
  ExcanRoleRepository roleRepository;
  @Autowired
  ExcanPerRoleRepository perRoleRepository;

  @Autowired
  ExcanPermissionRepository permissionRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    M00EmployeeJpo user = employeeRepository.findByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with UserName: " + username));
    Optional<ExcanRoleJpo> role = roleRepository.findByName(user.getRole());
    String roleName = "ROLE_STAFF";
    if(role.isPresent()){
      //roleName = role.get().getName().name();
      roleName = role.get().getName();
    }
    return EmployeeDetailsImpl.build(user, null);
  }

  @Transactional
  public UserDetails loadUserById(String id) throws RuntimeException {
    M00EmployeeJpo user = employeeRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Id: " + id));
    Optional<ExcanRoleJpo> role = roleRepository.findByName(user.getRole());
    if(role.isPresent()){
      List<Integer> perIds = perRoleRepository.findByName(user.getRole()).stream().map(ExcanPerRoleJpo::getPermissionId).collect(Collectors.toList());
      Set<String> perName = perIds.stream().map(i -> Objects.requireNonNull(permissionRepository.findById(i).orElse(null)).getName()).collect(Collectors.toSet());
      return EmployeeDetailsImpl.build(user, perName);
    }
    return new EmployeeDetailsImpl(user.getEmpId(), user.getName(), user.getMail(),user.getAvatar(), user.getPassword(),user.getRole(),null);
  }

}
