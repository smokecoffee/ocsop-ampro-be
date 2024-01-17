package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;

import java.util.List;

public interface ExcanUserService {

    public ExcanUser find(String id);

    public List<ExcanUser> findAll();

    public void modify(List<ExcanUser> entityList);

    public ExcanUser register(ExcanUser entity);

    public void remove(String id);

    boolean existsByName(String userName);

    boolean existsByEmail(String email);

    List<ExcanUser> findAllByIdIn(List<String> id);
}
