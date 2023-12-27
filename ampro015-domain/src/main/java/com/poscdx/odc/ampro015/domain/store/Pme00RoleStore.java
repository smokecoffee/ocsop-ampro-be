package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Role;

import java.util.List;

public interface Pme00RoleStore {
    Pme00Role retrieve(int id);

    List<Pme00Role> retrieveAll();

    Pme00Role update(Pme00Role Pme00Role);

    Pme00Role create(Pme00Role entity);

    void delete(int id);

}
