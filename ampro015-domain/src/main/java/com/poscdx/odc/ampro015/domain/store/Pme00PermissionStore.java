package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Permission;

import java.util.List;

public interface Pme00PermissionStore {

    public Pme00Permission retrieve(int id);

    public List<Pme00Permission> retrieveAll();

    public Pme00Permission update(Pme00Permission entity);

    public Pme00Permission create(Pme00Permission entity);

    public void delete(int id);
}
