package com.poscodx.odc.ampro015.store;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.poscdx.odc.ampro015.domain.entity.SCodeItem;
import com.poscdx.odc.ampro015.domain.store.SCodeItemStore;
import com.poscodx.odc.ampro015.store.jpo.SCodeItemJpo;
import com.poscodx.odc.ampro015.store.jpo.SCodeItemJpoId;
import com.poscodx.odc.ampro015.store.repository.SCodeItemRepository;
import org.springframework.stereotype.Repository;


/**
 * Auto generated class
 * 
 * 자동생성 프로그램 버전 : 1.0.0
 * 생성일시 :  2023-08-26 21:42:35.32
 * @FileName : 클래스에 대한 한글 명칭
 * Change history
 * @수정날짜;SCR_NO;수정자;수정내용
 * @2023-08-26 21:42:35.32;00000;홍길동;최초생성
 * 
 */
@Repository
public class SCodeItemJpaStore
    implements SCodeItemStore
{
    private final SCodeItemRepository repository;

    public SCodeItemJpaStore(SCodeItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public SCodeItem retrieve(String itemNum, BigDecimal poLineNum, String poNum) {
        SCodeItemJpoId param = new SCodeItemJpoId();
        param.setItemNum(itemNum);
        param.setPoLineNum(poLineNum);
        param.setPoNum(poNum);
        Optional<SCodeItemJpo> retVal = this.repository.findById(param);
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<SCodeItem> retrieveAll() {
        List<SCodeItemJpo> retVal = this.repository.findAll();
        return SCodeItemJpo.toDomains(retVal);
    }

    @Override
    public SCodeItem update(SCodeItem entity) {
        SCodeItemJpo jpoToUpdate = new SCodeItemJpo(entity);
        SCodeItemJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public SCodeItem create(SCodeItem entity) {
        SCodeItemJpo jpoToSave = new SCodeItemJpo(entity);
        SCodeItemJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(String itemNum, BigDecimal poLineNum, String poNum) {
        SCodeItemJpoId param = new SCodeItemJpoId();
        param.setItemNum(itemNum);
        param.setPoLineNum(poLineNum);
        param.setPoNum(poNum);
        this.repository.deleteById(param);
    }
}
