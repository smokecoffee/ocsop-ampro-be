package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.S91Menu;
import com.poscdx.odc.ampro015.domain.spec.S91MenuService;
import com.poscdx.odc.ampro015.domain.store.S91MenuStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class S91MenuLogic implements S91MenuService {
    private final S91MenuStore store;

    public S91MenuLogic(S91MenuStore store) {
        this.store = store;
    }

    @Override
    public S91Menu find(String id) {
        return store.retrieve(id);
    }

    @Override
    public List<S91Menu> findAll() {
        return store.retrieveAll();
    }

    @Override
    public List<S91Menu> findMenuByPermission(List<String> permissionList) {
        if (permissionList == null || permissionList.isEmpty()) {
            permissionList = new ArrayList<>();
            permissionList.add("");
        }
        return store.findMenuByPermission(permissionList);
    }

}
