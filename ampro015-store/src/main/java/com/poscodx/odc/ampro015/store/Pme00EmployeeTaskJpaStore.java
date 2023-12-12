package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeTaskStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00EmployeeTaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        List<Pme00EmployeeTaskJpo> newPme00EmployeeTaskJpos = new ArrayList<>();

        pme00EmployeeTaskList.stream().forEach(pme00EmployeeTask -> {

        });

        List<Pme00EmployeeTaskJpo> responseList = this.repository.saveAll(newPme00EmployeeTaskJpos);

        return responseList.stream().map(responsejpo -> responsejpo.toDomain()).collect(Collectors.toList());
    }

    @Override
    public List<Pme00EmployeeTask> retrieveAllByTaskId(M00TaskId reqM00TaskId) {
        List<Pme00EmployeeTaskJpo> responseList = this.repository.findAllByM00TaskId(reqM00TaskId.getProjectNumber(), reqM00TaskId.getTaskName());
        return responseList.stream().map(responsejpo -> responsejpo.toDomain()).collect(Collectors.toList());
    }

    @Override
    public void deleteByListEmployeeTask(List<Pme00EmployeeTask> listRemove) {
        StringBuilder condition = new StringBuilder();
        condition.append("(");
        listRemove.stream().forEach(pme00EmployeeTask -> {
            condition.append(" ('");
            condition.append(String.join("','", pme00EmployeeTask.getProjectNumber(), pme00EmployeeTask.getTaskName(), pme00EmployeeTask.getTaskName(), pme00EmployeeTask.getEmpId(), pme00EmployeeTask.getEmpName()));
            condition.append("')");
        });

        condition.append(")");
        System.out.println("deleteQuery: "+ condition.toString());
        this.repository.deleteMultipleRowById(condition.toString());


    }
}
