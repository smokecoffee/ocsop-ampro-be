package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;

import java.util.List;


public interface ExcanUserStore {

    public ExcanUser retrieve(String id);

    public List<ExcanUser> retrieveAll();

    public ExcanUser update(ExcanUser entity);

    public ExcanUser create(ExcanUser entity);

    public void delete(String id);

    boolean existsByName(String userName);

    boolean existsByEmail(String email);

    ExcanUser findById(String id);

    //    ExcanUser findByEmail(String email);
    List<ExcanUser> findAll();

    List<ExcanUser> findAllByIdIn(List<String> ids);
}
