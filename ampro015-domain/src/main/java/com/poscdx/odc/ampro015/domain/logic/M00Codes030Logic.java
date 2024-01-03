package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.M00Codes030;
import com.poscdx.odc.ampro015.domain.entity.M00Codes030Id;
import com.poscdx.odc.ampro015.domain.entity.TaskStatusDto;
import com.poscdx.odc.ampro015.domain.spec.M00Codes030Service;
import com.poscdx.odc.ampro015.domain.store.M00Codes030Store;

import java.util.ArrayList;
import java.util.List;

/**
 * M00Codes030Logic
 *
 * @author 202284_Lam
 * @since 2023-11-28
 */
public class M00Codes030Logic implements M00Codes030Service{

    public final M00Codes030Store store;

    public M00Codes030Logic(M00Codes030Store store){
        this.store = store;
    }
    @Override
    public List<M00Codes030> findAll(){
        return this.store.retrieveAll();
    }

    @Override
    public M00Codes030 find(M00Codes030Id id){
        return this.store.retrieve(id);
    }

    @Override
    public void modify(List<M00Codes030> entityList){
        entityList.forEach(this.store::update);
    }

    @Override
    public M00Codes030 register(M00Codes030 entity){
        return this.store.create(entity);
    }

    @Override
    public void remove(M00Codes030Id id){
        this.store.delete(id);
    }

    @Override
    public int getMaxSeqInquiry(int cdTpId, int cateGroupId){
        return this.store.getMaxSeqInquiry(cdTpId, cateGroupId);
    }

    @Override
    public List<M00Codes030>  findM00Codes030(String cdV, String meaning){
        return this.store.findM00Codes030(cdV, meaning);
    }

    @Override
    public List<M00Codes030> findM00Codes030ById(int cdTpId){
        return this.store.findM00Codes030ById(cdTpId);
    }

    @Override
    public List<TaskStatusDto> getTaskStatus() {
        List<Object[]> resultList = this.store.getTaskStatus();
        List<TaskStatusDto> taskStatusDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            taskStatusDtoList.add(new TaskStatusDto(obj));
        }
        return taskStatusDtoList;
    }
}
