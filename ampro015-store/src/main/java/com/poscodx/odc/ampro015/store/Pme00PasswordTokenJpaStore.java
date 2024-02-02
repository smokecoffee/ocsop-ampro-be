package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00PasswordToken;
import com.poscdx.odc.ampro015.domain.store.Pme00PasswordTokenStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00PasswordTokenJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00PasswordTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00PasswordTokenJpaStore
    implements Pme00PasswordTokenStore
{
    private final Pme00PasswordTokenRepository repository;

    public Pme00PasswordTokenJpaStore(Pme00PasswordTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00PasswordToken retrieve(int id) {
        Optional<Pme00PasswordTokenJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00PasswordTokenJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00PasswordToken> retrieveAll() {
        List<Pme00PasswordTokenJpo> retVal = this.repository.findAll();
        return Pme00PasswordTokenJpo.toDomains(retVal);
    }

    @Override
    public Pme00PasswordToken update(Pme00PasswordToken entity) {
        Pme00PasswordTokenJpo jpoToUpdate = new Pme00PasswordTokenJpo(entity);
        Pme00PasswordTokenJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00PasswordToken create(Pme00PasswordToken entity) {
        Pme00PasswordTokenJpo jpoToSave = new Pme00PasswordTokenJpo(entity);
        Pme00PasswordTokenJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Object[]> FindPasswordTokenByToken(String token) {
        return this.repository.FindPasswordTokenByToken(token);
    }
}
