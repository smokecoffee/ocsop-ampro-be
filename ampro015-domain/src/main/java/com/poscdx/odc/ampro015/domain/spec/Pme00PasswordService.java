package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00Password;

import java.util.List;

public interface Pme00PasswordService {

    public Pme00Password find(int id);

    public List<Pme00Password> findAll();

    public void modify(List<Pme00Password> entityList);

    public Pme00Password register(Pme00Password entity);

    public void remove(int id);
}
