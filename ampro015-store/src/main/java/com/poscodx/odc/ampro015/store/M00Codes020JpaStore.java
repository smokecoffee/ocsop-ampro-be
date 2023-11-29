package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00Codes020;
import com.poscdx.odc.ampro015.domain.store.M00Codes020Store;
import com.poscodx.odc.ampro015.store.jpo.M00Codes020Jpo;
import com.poscodx.odc.ampro015.store.repository.M00Codes020Repository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class M00Codes020JpaStore implements M00Codes020Store {

    public final M00Codes020Repository repository;

    public M00Codes020JpaStore(M00Codes020Repository repository){
        this.repository = repository;
    }

    @Override
    public List<M00Codes020> retrieveAll(){
        return M00Codes020Jpo.toDomains(this.repository.findAll());
    }

    @Override
    public M00Codes020 retrieve(int cdTpId){
        Optional<M00Codes020Jpo> retVal = this.repository.findById(cdTpId);
        return retVal.map(M00Codes020Jpo::toDomain).orElse(null);
    }

}
