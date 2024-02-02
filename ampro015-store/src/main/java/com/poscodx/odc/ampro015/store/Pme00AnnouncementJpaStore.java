package com.poscodx.odc.ampro015.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poscdx.odc.ampro015.domain.entity.Pme00Announcement;
import com.poscdx.odc.ampro015.domain.store.Pme00AnnouncementStore;
import com.poscodx.odc.ampro015.store.jpo.Pme00AnnouncementJpo;
import com.poscodx.odc.ampro015.store.repository.Pme00AnnouncementRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class Pme00AnnouncementJpaStore implements Pme00AnnouncementStore {

    private final Pme00AnnouncementRepository repository;

    public Pme00AnnouncementJpaStore(Pme00AnnouncementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pme00Announcement retrieve(int id) {
        Optional<Pme00AnnouncementJpo> retVal = this.repository.findById(id);
        return retVal.map(Pme00AnnouncementJpo::toDomain).orElse(null);
    }

    @Override
    public List<Pme00Announcement> retrieveAll() {
        List<Map<String, String>> mapList = this.repository.findAllAnnouncement();
        ObjectMapper mapper = new ObjectMapper();
        return mapList.stream().map(item -> mapper.convertValue(item, Pme00Announcement.class)).collect(Collectors.toList());
    }

    @Override
    public List<Pme00Announcement> retrieveByConditions(String name, int type, String status, Date date) {
        List<Map<String, String>> mapList = this.repository.findByConditions(name, type, status, date);
        ObjectMapper mapper = new ObjectMapper();
        return mapList.stream().map(item -> mapper.convertValue(item, Pme00Announcement.class)).collect(Collectors.toList());
    }

    @Override
    public Pme00Announcement update(Pme00Announcement Pme00Announcement) {
        Pme00AnnouncementJpo jpoToUpdate = new Pme00AnnouncementJpo(Pme00Announcement);
        Pme00AnnouncementJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public Pme00Announcement create(Pme00Announcement entity) {
        return this.repository.save(new Pme00AnnouncementJpo(entity)).toDomain();
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
