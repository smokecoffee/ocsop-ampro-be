package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00PasswordToken;

import java.util.List;

public interface Pme00PasswordTokenStore {

    public Pme00PasswordToken retrieve(int id);

    public List<Pme00PasswordToken> retrieveAll();

    public Pme00PasswordToken update(Pme00PasswordToken entity);

    public Pme00PasswordToken create(Pme00PasswordToken entity);

    public void delete(int id);

    List<Object[]> FindPasswordTokenByToken(String token);
}
