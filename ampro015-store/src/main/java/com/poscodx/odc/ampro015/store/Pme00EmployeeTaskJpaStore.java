package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeTaskStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00EmployeeTaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class Pme00EmployeeTaskJpaStore implements Pme00EmployeeTaskStore {

    private final Pme00EmployeeTaskRepository repository;

    public Pme00EmployeeTaskJpaStore(Pme00EmployeeTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00EmployeeTask retrieve(M00EmployeeTaskId m00EmployeeTaskId) {
        Optional<Pme00EmployeeTaskJpo> retVal = this.repository.findById(m00EmployeeTaskId);
        return retVal.map(Pme00EmployeeTaskJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00EmployeeTask> retrieveAll() {
        return Pme00EmployeeTaskJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00EmployeeTask update(Pme00EmployeeTask Pme00EmployeeTask) {
        Pme00EmployeeTaskJpo jpoToUpdate = new Pme00EmployeeTaskJpo(Pme00EmployeeTask);
        Pme00EmployeeTaskJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00EmployeeTask create(Pme00EmployeeTask entity) {
        return this.repository.save(new Pme00EmployeeTaskJpo(entity)).toDomain();
    }

    @Override
    public void delete(M00EmployeeTaskId m00EmployeeTaskId) {
        this.repository.deleteById(m00EmployeeTaskId);
    }

    @Override
    public List<Pme00EmployeeTask> createFromList(List<Pme00EmployeeTask> pme00EmployeeTaskList) {

        List<Pme00EmployeeTaskJpo> newPme00EmployeeTaskJpos = pme00EmployeeTaskList.stream().map(pme00EmployeeTask -> new Pme00EmployeeTaskJpo(pme00EmployeeTask)).collect(Collectors.toList());
        List<Pme00EmployeeTaskJpo> responseList = this.repository.saveAll(newPme00EmployeeTaskJpos);

        return responseList.stream().map(responsejpo -> responsejpo.toDomain()).collect(Collectors.toList());
    }
}
