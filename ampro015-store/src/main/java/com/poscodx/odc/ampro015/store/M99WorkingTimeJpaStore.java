package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M99WorkingTime;
import com.poscdx.odc.ampro015.domain.store.M99WorkingTimeStore;
import com.poscodx.odc.ampro015.store.jpo.M99WorkingTimeJpo;
import com.poscodx.odc.ampro015.store.repository.M99WorkingTimeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class M99WorkingTimeJpaStore implements M99WorkingTimeStore {

    public final M99WorkingTimeRepository repository;

    public M99WorkingTimeJpaStore(M99WorkingTimeRepository repository){
        this.repository = repository;
    }

    @Override
    public List<M99WorkingTime> retrieveAll(){
        return M99WorkingTimeJpo.toDomains(this.repository.findAll());
    }

}
