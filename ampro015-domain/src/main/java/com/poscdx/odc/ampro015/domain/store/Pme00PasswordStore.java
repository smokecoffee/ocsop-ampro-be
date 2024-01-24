package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Password;

import java.util.List;

public interface Pme00PasswordStore {

    public Pme00Password retrieve(int id);

    public List<Pme00Password> retrieveAll();

    public Pme00Password update(Pme00Password entity);

    public Pme00Password create(Pme00Password entity);

    public void delete(int id);
}
