package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Pme00PasswordToken;

import java.util.List;

public interface Pme00PasswordTokenService {

    public Pme00PasswordToken find(int id);

    public List<Pme00PasswordToken> findAll();

    public void modify(List<Pme00PasswordToken> entityList);

    public Pme00PasswordToken register(Pme00PasswordToken entity);

    public void remove(int id);

    //void findEmail(String );
}
