package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeeting;
import com.poscdx.odc.ampro015.domain.store.Pme00EmployeeMeetingStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeMeetingJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00EmployeeMeetingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Pme00EmployeeMeetingJpaStore implements Pme00EmployeeMeetingStore {

    private final Pme00EmployeeMeetingRepository repository;

    public Pme00EmployeeMeetingJpaStore(Pme00EmployeeMeetingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00EmployeeMeeting retrieve(int id) {
        Optional<Pme00EmployeeMeetingJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00EmployeeMeetingJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00EmployeeMeeting> retrieveAll() {
        return Pme00EmployeeMeetingJpo.toDomains(this.repository.findAll());
    }

    @Override
    public Pme00EmployeeMeeting update(Pme00EmployeeMeeting Pme00EmployeeMeeting) {
        Pme00EmployeeMeetingJpo jpoToUpdate = new Pme00EmployeeMeetingJpo(Pme00EmployeeMeeting);
        Pme00EmployeeMeetingJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00EmployeeMeeting create(Pme00EmployeeMeeting entity) {
        return this.repository.save(new Pme00EmployeeMeetingJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Pme00EmployeeMeeting> findByMeetingId(int id) {
        return Pme00EmployeeMeetingJpo.toDomains(this.repository.findByMeetingId(id));
    }

    @Override
    public  void deleteAllByMeetingId(int meetingId){
        this.repository.deleteAllByMeetingId(meetingId);
    }

}
