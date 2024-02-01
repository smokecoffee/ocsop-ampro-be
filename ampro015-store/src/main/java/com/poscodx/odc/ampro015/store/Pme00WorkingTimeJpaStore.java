package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00WorkingTime;
import com.poscdx.odc.ampro015.domain.store.Pme00WorkingTimeStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00WorkingTimeJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00WorkingTimeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Pme00WorkingTimeJpaStore implements Pme00WorkingTimeStore {

    public final Pme00WorkingTimeRepository repository;

    public Pme00WorkingTimeJpaStore(Pme00WorkingTimeRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Pme00WorkingTime> retrieveAll(){
        return Pme00WorkingTimeJpo.toDomains(this.repository.findAll());
    }

}
