package com.poscodx.odc.ampro015.config.services;

import com.poscdx.odc.ampro015.domain.entity.Pme00RoleUser;
import com.poscodx.odc.ampro015.store.Pme00RoleUserJpaStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00PerRoleJpo;
import com.poscodx.odc.ampro015.store.jpo.Pme00PermissionJpo;
import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00PerRoleRepository;
import com.poscodx.odc.ampro015.store.repository.Pme00PermissionRepository;
import com.poscodx.odc.ampro015.store.repository.M00EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
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
    Pme00PerRoleRepository roleRepository;
    @Autowired
    Pme00PerRoleRepository perRoleRepository;
    @Autowired
    Pme00PermissionRepository permissionRepository;
    @Autowired
    Pme00RoleUserJpaStore pme00RoleUserJpaStore;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        M00EmployeeJpo user = employeeRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with UserName: " + username));

        List<Pme00RoleUser> roles = pme00RoleUserJpaStore.findRoleUserByEmpId(user.getEmpId());

        String roleName = "ROLE_STAFF";
        if (!roles.isEmpty()) {
            // roleName = role.get().getName().name();
            //roleName = role.get().getName();
        }
        return EmployeeDetailsImpl.build(user, null);
    }

    @Transactional
    public UserDetails loadUserById(String id) throws RuntimeException {
        M00EmployeeJpo user = employeeRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with Id: " + id));

        List<Pme00RoleUser> roles = pme00RoleUserJpaStore.findRoleUserByEmpId(user.getEmpId());
        // Optional<Pme00RoleJpo> role = roleRepository.findByName(user.getRole());

        if (!roles.isEmpty()) {
            // List<Integer> perIds =
            // perRoleRepository.findByRoleId(user.getRole()).stream().map(Pme00PerRoleJpo::getPermissionId).collect(Collectors.toList());
            // Set<String> perName = perIds.stream().map(i ->
            // Objects.requireNonNull(permissionRepository.findById(i).orElse(null)).getName()).collect(Collectors.toSet());
            // return EmployeeDetailsImpl.build(user, perName);
            Set<String> perName = new HashSet<>();
            for (Pme00RoleUser roleUser : roles) {
                List<Integer> perIds = perRoleRepository.findByRoleId(roleUser.getRoleId()).stream()
                        .map(Pme00PerRoleJpo::getPermissionId).collect(Collectors.toList());
                perIds.forEach(permissionId -> {
                    Optional<Pme00PermissionJpo> permission = permissionRepository.findById(permissionId);
                    if (permission.isPresent()) {
                        perName.add(permission.get().getName());
                    }
                });
            }
            return EmployeeDetailsImpl.build(user, perName);
        }
        return new EmployeeDetailsImpl(user.getEmpId(), user.getName(), user.getMail(),user.getAvatar(), user.getPassword(),user.getRole(),null);
    }

}
