package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.S91Menu;

import java.util.List;

public interface S91MenuService {
    S91Menu find(String id);
    List<S91Menu> findAll();
    List<S91Menu> findMenuByPermission(List<String> permissionList);
}
