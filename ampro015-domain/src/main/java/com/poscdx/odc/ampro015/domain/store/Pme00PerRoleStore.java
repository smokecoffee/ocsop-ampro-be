package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00PerRole;

import java.util.List;

public interface Pme00PerRoleStore {

    public Pme00PerRole retrieve(int id);

    public List<Pme00PerRole> retrieveAll();

    public Pme00PerRole update(Pme00PerRole entity);

    public Pme00PerRole create(Pme00PerRole entity);

    public void delete(int id);
}
