package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.ExcanPerRole;

import java.util.List;

public interface ExcanPerRoleStore {

    public ExcanPerRole retrieve(int id);

    public List<ExcanPerRole> retrieveAll();

    public ExcanPerRole update(ExcanPerRole entity);

    public ExcanPerRole create(ExcanPerRole entity);

    public void delete(int id);
}
