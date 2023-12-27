package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00RoleUser;

import java.util.List;

public interface Pme00RoleUserService {

    Pme00RoleUser find(int id);

    List<Pme00RoleUser> findAll();

    void modify(List<Pme00RoleUser> entityList);

    Pme00RoleUser register(Pme00RoleUser entity);

    void remove(int id);

}
