package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.S91Menu;

import java.util.List;
import java.util.Set;

public interface S91MenuStore {
    S91Menu retrieve(String id);
    List<S91Menu> retrieveAll();
    List<S91Menu> findMenuByPermission(List<String> permissionList);
}
