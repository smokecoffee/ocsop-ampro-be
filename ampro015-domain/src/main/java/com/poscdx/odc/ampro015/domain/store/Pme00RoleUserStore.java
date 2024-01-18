package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00RoleUser;

import java.util.List;

public interface Pme00RoleUserStore {
    Pme00RoleUser retrieve(int id);

    List<Pme00RoleUser> retrieveAll();

    Pme00RoleUser update(Pme00RoleUser Pme00RoleUser);

    Pme00RoleUser create(Pme00RoleUser entity);

    void delete(int id);

    List<Pme00RoleUser> findByEmpId(String name);

    List<Pme00RoleUser> findByRoleId(int name);
}
