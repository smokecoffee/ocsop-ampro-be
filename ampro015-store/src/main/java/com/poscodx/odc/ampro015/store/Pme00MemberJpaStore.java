package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;
import com.poscdx.odc.ampro015.domain.store.Pme00MemberStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00MemberJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00MemberJpaStore implements Pme00MemberStore {

    private final Pme00MemberRepository repository;

    public Pme00MemberJpaStore(Pme00MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00Member retrieve(int id) {
        Optional<Pme00MemberJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00MemberJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00Member> retrieveAll() {
        return Pme00MemberJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00Member update(Pme00Member pme00Member) {
        Pme00MemberJpo jpoToUpdate = new Pme00MemberJpo(pme00Member);
        Pme00MemberJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00Member create(Pme00Member entity) {
        return this.repository.save(new Pme00MemberJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
