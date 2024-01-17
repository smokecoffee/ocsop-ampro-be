package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.ERole;
import com.poscdx.odc.ampro015.domain.entity.ExcanRole;
import com.poscdx.odc.ampro015.domain.entity.payload.response.RoleResponse;
import com.poscdx.odc.ampro015.domain.spec.ExcanRoleService;
import com.poscdx.odc.ampro015.domain.store.ExcanRoleStore;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcanRoleLogic implements ExcanRoleService
{
    private final ExcanRoleStore store;

    public ExcanRoleLogic(ExcanRoleStore store) {
        this.store = store;
    }

    @Override
    public ExcanRole find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<ExcanRole> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public ExcanRole register(ExcanRole entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<ExcanRole> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    @Override
    public RoleResponse addRole(ExcanRole excanRole) {
        RoleResponse result = new RoleResponse();
        if(!store.findByName(ERole.valueOf(excanRole.getName())).isEmpty()){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("Role name " + excanRole.getName() + " already exist!");
        } else {
            store.create(excanRole);
            result.setCode(HttpStatus.SC_CREATED);
            List<ExcanRole> data = new ArrayList<>();
            data.add(excanRole);
            result.setData(data);
            result.setMessage("Create role successfully!");
        }
        return result;
    }

    @Override
    public RoleResponse updateRole(ExcanRole excanRole) {
        RoleResponse result = new RoleResponse();
        if(store.findById(excanRole.getId()) == null){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("Role Id " + excanRole.getId() + " not exist!");
        } else if(existRoleNameWithOutCurrentRoLe(excanRole.getId(), excanRole.getName())){
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMessage("Role name " + excanRole.getName() + " already exist!");
        } else {
            store.update(excanRole);
            result.setCode(HttpStatus.SC_OK);
            List<ExcanRole> data = new ArrayList<>();
            data.add(excanRole);
            result.setData(data);
            result.setMessage("Update role successfully!");
        }
        return result;
    }

    @Override
    public RoleResponse findRoleByCondition(String roleName, String statusId) {
        RoleResponse result = new RoleResponse();
        List<ExcanRole> searchRoles = store.findAll();

        searchRoles = searchRoles.stream().filter(i -> StringUtils.containsIgnoreCase(i.getName(), roleName)).collect(Collectors.toList());
        if(!statusId.equalsIgnoreCase("all")){
            searchRoles = searchRoles.stream().filter(i -> i.getStatus() == Integer.parseInt(statusId)).collect(Collectors.toList());
        }

        result.setData(searchRoles);
        result.setCode(HttpStatus.SC_OK);
        result.setMessage("Get role by condition successfully!");
        return result;
    }

    public boolean existRoleNameWithOutCurrentRoLe(int id, String roleName){
        ExcanRole currentRole = store.findById(id);
        if(currentRole.getName().equals(roleName)){
            return false;
        } else {
            List<ExcanRole> allRole = store.findAll();
            List<String> allEmail = allRole.stream().map(ExcanRole::getName).collect(Collectors.toList());
            return allEmail.contains(roleName);
        }
    }
}
