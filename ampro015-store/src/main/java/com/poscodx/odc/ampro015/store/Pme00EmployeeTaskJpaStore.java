package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeTask;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeTaskStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00EmployeeTaskRepository;
import com.poscoict.base.share.util.string.StringUtil;
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
    public List<Pme00EmployeeTask> retrieveAllByProjectNumber(String projectNumber) {
        return Pme00EmployeeTaskJpo.toDomains(this.repository.findAllByProjectNumber(projectNumber));
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

        //TODO: convert dto -> jpo for Pme00EmployeeTask
        List<Pme00EmployeeTaskJpo> requestEmployeeTaskJpoList = new ArrayList<>();

        pme00EmployeeTaskList.stream().forEach(pme00EmployeeTask -> {
            Pme00EmployeeTaskJpo pme00EmployeeTaskJpo = new Pme00EmployeeTaskJpo();
            pme00EmployeeTaskJpo.setProjectNumber(pme00EmployeeTask.getProjectNumber());
            pme00EmployeeTaskJpo.setTaskName(pme00EmployeeTask.getTaskName());
            pme00EmployeeTaskJpo.setEmpId(pme00EmployeeTask.getEmpId());
            pme00EmployeeTaskJpo.setEmpName(StringUtil.defaultIfBlank(pme00EmployeeTask.getEmpName(), ""));
            pme00EmployeeTaskJpo.setAvatar(StringUtil.defaultIfBlank(pme00EmployeeTask.getAvatar(), ""));
            requestEmployeeTaskJpoList.add(pme00EmployeeTaskJpo);
        });

        List<Pme00EmployeeTaskJpo> responseList = new ArrayList<>();
        this.repository.saveAll(requestEmployeeTaskJpoList);

        return responseList.stream().map(responsejpo -> responsejpo.toDomain()).collect(Collectors.toList());
    }

    @Override
    public List<Pme00EmployeeTask> retrieveAllByTaskId(M00TaskId reqM00TaskId) {
        List<Pme00EmployeeTaskJpo> responseList = this.repository.findAllByM00TaskId(reqM00TaskId.getProjectNumber(), reqM00TaskId.getTaskName());
        return responseList.stream().map(responsejpo -> responsejpo.toDomain()).collect(Collectors.toList());
    }
}
