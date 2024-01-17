package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.ExcanPermission;

import java.util.List;

public interface ExcanPermissionService {

    public ExcanPermission find(int id);

    public List<ExcanPermission> findAll();

    public void modify(List<ExcanPermission> entityList);

    public ExcanPermission register(ExcanPermission entity);

    public void remove(int id);
}
