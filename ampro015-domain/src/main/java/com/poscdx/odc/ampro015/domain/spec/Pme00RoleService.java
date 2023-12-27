package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Role;

import java.util.List;

public interface Pme00RoleService {

    Pme00Role find(int id);

    List<Pme00Role> findAll();

    void modify(List<Pme00Role> entityList);

    Pme00Role register(Pme00Role entity);

    void remove(int id);

}
