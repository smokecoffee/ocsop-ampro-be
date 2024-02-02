package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00RoleUser;
import com.poscdx.odc.ampro015.domain.spec.Pme00RoleUserService;
import com.poscdx.odc.ampro015.domain.store.Pme00RoleUserStore;

import java.util.List;

public class Pme00RoleUserLogic implements Pme00RoleUserService {
    private final Pme00RoleUserStore store;

    public Pme00RoleUserLogic(Pme00RoleUserStore store) {
        this.store = store;
    }

    @Override
    public Pme00RoleUser find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00RoleUser> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00RoleUser> entityList) {
        List<Pme00RoleUser> deletedList = store.findRoleUserByEmpId(entityList.get(0).getEmpId());
        for (Pme00RoleUser user : deletedList) {
            store.delete(user.getId());
        }
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00RoleUser register(Pme00RoleUser entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    @Override
    public  List<Pme00RoleUser> findRoleUserByEmpId(String empId){
        return this.store.findRoleUserByEmpId(empId);
    }

}
