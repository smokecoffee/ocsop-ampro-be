package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Password;
import com.poscdx.odc.ampro015.domain.store.Pme00PasswordStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00PasswordJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00PasswordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00PasswordJpaStore
    implements Pme00PasswordStore
{
    private final Pme00PasswordRepository repository;

    public Pme00PasswordJpaStore(Pme00PasswordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00Password retrieve(int id) {
        Optional<Pme00PasswordJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00PasswordJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00Password> retrieveAll() {
        List<Pme00PasswordJpo> retVal = this.repository.findAll();
        return Pme00PasswordJpo.toDomains(retVal);
    }

    @Override
    public Pme00Password update(Pme00Password entity) {
        Pme00PasswordJpo jpoToUpdate = new Pme00PasswordJpo(entity);
        Pme00PasswordJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00Password create(Pme00Password entity) {
        Pme00PasswordJpo jpoToSave = new Pme00PasswordJpo(entity);
        Pme00PasswordJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
