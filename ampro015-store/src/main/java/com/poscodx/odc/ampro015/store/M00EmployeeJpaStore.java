package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.M00Employee;
import com.poscdx.odc.ampro015.domain.store.M00EmployeeStore;
import com.poscodx.odc.ampro015.store.jpo.M00EmployeeJpo;
import com.poscodx.odc.ampro015.store.repository.M00EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class M00EmployeeJpaStore implements M00EmployeeStore {
    private final M00EmployeeRepository repository;

    public M00EmployeeJpaStore(M00EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public M00Employee retrieve(String id) {
        Optional<M00EmployeeJpo> retVal = this.repository.findById(id);
        return retVal.map(M00EmployeeJpo::toDomain).orElse(null);
    }

    @Override
    public M00Employee update(M00Employee entity) {
        M00EmployeeJpo jpoToUpdate = new M00EmployeeJpo(entity);
        M00EmployeeJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public M00Employee create(M00Employee entity) {
        return this.repository.save(new M00EmployeeJpo(entity)).toDomain();
    }


    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<M00Employee> retrieveAll() {
        return M00EmployeeJpo.toDomains(this.repository.findAll());
    }


    @Override
    public List<Object[]> getEmployeeByEmployeeId(Set<String> empId) {
        return this.repository.getEmployeeByEmployeeId(empId);
    }

    @Override
    public List<Object[]> getActiveEmployee() {
        return this.repository.getActiveEmployee();
    }

    @Override
    public List<Object[]> findAllEmployee() {
        return this.repository.findAllEmployee();
    }

    @Override
    public List<Object[]> searchPmeEmployee(String site, String status, String name, String empId, String joinDateFrom, String joinDateTo, String gender) {
        return this.repository.searchPmeEmployee(site, status, name, empId, joinDateFrom, joinDateTo, gender);
    }
}
