package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscdx.odc.ampro015.domain.store.M00Codes030Store;
import com.poscodx.odc.ampro015.store.jpo.M00Codes030Jpo;
import com.poscodx.odc.ampro015.store.repository.M00Codes030Repository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * M00Codes030JpaStore
 *
 * @author 202284_Lam
 * @since 2023-11-28
 */
@Repository
public class M00Codes030JpaStore implements M00Codes030Store {

    public final M00Codes030Repository repository;

    public M00Codes030JpaStore(M00Codes030Repository repository){
        this.repository = repository;
    }

    @Override
    public List<M00Codes030> retrieveAll(){
        return M00Codes030Jpo.toDomains(this.repository.findAll());
    }

    @Override
    public M00Codes030 retrieve(M00Codes030Id id){
        Optional<M00Codes030Jpo> retVal = this.repository.findById(id);
        return retVal.map(M00Codes030Jpo::toDomain).orElse(null);
    }

    @Override
    public M00Codes030 update(M00Codes030 entity){
        M00Codes030Jpo jpoToUpdate = new M00Codes030Jpo(entity);
        M00Codes030Jpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public M00Codes030 create(M00Codes030 entity){
        return this.repository.save(new M00Codes030Jpo(entity)).toDomain();
    }

    @Override
    public void delete(M00Codes030Id id){
        this.repository.deleteById(id);
    }

    @Override
    public int getMaxSeqInquiry(int cdTpId, int cateGroupId){
        return this.repository.getMaxSeqInquiry(cdTpId, cateGroupId);
    }

    @Override
    public List<M00Codes030>  findM00Codes030(String cdV, String meaning){
        return M00Codes030Jpo.toDomains(this.repository.findM00Codes030(cdV, meaning));
    }

    @Override
    public List<M00Codes030>  findM00Codes030ById(int cdTpId){
        return M00Codes030Jpo.toDomains(this.repository.findM00Codes030ById(cdTpId));
    }

    @Override
    public List<Object[]> getTaskStatus() {
        return this.repository.getTaskStatus();
    }

    @Override
    public List<M00Codes030> getListProject(String user){
        return M00Codes030Jpo.toDomains(this.repository.getListProject(user));
    };
}