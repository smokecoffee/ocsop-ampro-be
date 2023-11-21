package com.poscodx.odc.ampro015.store;

import java.util.List;
import java.util.Optional;
import com.poscdx.odc.ampro015.domain.entity.QCodeItem;
import com.poscdx.odc.ampro015.domain.store.QCodeItemStore;
import com.poscodx.odc.ampro015.store.jpo.QCodeItemJpo;
import com.poscodx.odc.ampro015.store.repository.QCodeItemRepository;
import org.springframework.stereotype.Repository;


/**
 * Auto generated class
 * 
 * 자동생성 프로그램 버전 : 1.0.0
 * 생성일시 :  2023-08-26 21:42:34.446
 * @FileName : 클래스에 대한 한글 명칭
 * Change history
 * @수정날짜;SCR_NO;수정자;수정내용
 * @2023-08-26 21:42:34.446;00000;홍길동;최초생성
 * 
 */
@Repository
public class QCodeItemJpaStore
    implements QCodeItemStore
{
    private final QCodeItemRepository repository;

    public QCodeItemJpaStore(QCodeItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public QCodeItem retrieve(String id) {
        Optional<QCodeItemJpo> retVal = this.repository.findById(id);
        if (retVal.isPresent()) {
            return retVal.get().toDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<QCodeItem> retrieveAll() {
        List<QCodeItemJpo> retVal = this.repository.findAll();
        return QCodeItemJpo.toDomains(retVal);
    }

    @Override
    public QCodeItem update(QCodeItem entity) {
        QCodeItemJpo jpoToUpdate = new QCodeItemJpo(entity);
        QCodeItemJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public QCodeItem create(QCodeItem entity) {
        QCodeItemJpo jpoToSave = new QCodeItemJpo(entity);
        QCodeItemJpo savedJpo = this.repository.save(jpoToSave);
        return savedJpo.toDomain();
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }
}
