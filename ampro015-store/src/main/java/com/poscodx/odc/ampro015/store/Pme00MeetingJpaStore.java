package com.poscodx.odc.ampro015.store;
import com.poscdx.odc.ampro015.domain.entity.Pme00Meeting;
import com.poscdx.odc.ampro015.domain.store.Pme00MeetingStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00MeetingJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00MeetingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00MeetingJpaStore implements Pme00MeetingStore {
    private final Pme00MeetingRepository repository;

    public Pme00MeetingJpaStore(Pme00MeetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00Meeting retrieve(int id) {
        Optional<Pme00MeetingJpo> retVal = this.repository.findById(id);
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<Pme00Meeting> retrieveAll() {
        return Pme00MeetingJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00Meeting update(Pme00Meeting pme00Meeting) {
        Pme00MeetingJpo jpoToUpdate = new Pme00MeetingJpo(pme00Meeting);
        Pme00MeetingJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00Meeting create(Pme00Meeting entity) {
        return this.repository.save(new Pme00MeetingJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
