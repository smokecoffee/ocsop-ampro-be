package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscodx.odc.ampro015.store.jpo.FieldJpo;
import com.poscodx.odc.ampro015.store.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FieldJpaStore implements FieldStore {

    @Autowired FieldRepository fieldRepository;

    @Override
    public Field add(Field field) {
        FieldJpo fieldJpo = new FieldJpo(field);
        FieldJpo addField = fieldRepository.save(fieldJpo);
        return addField.toDomain();
    }
}
