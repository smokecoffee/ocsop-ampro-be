package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.emun.SearchOperation;
import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscdx.odc.ampro015.domain.spec.SearchCriteria;
import com.poscdx.odc.ampro015.domain.spec.TaskSpecification;
import com.poscdx.odc.ampro015.domain.store.M00TaskStore;
import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscodx.odc.ampro015.store.repository.M00TaskRepository;

import com.poscoict.base.share.util.string.StringUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName.ACTUAL_END_DATE;
import static com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName.PROJECT_NUMBER;
import static com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName.TASK_NAME;
import static com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName.STATUS;
import static com.poscdx.odc.ampro015.domain.emun.M00TaskJpoComlumnName.PLAN_DATE;

@Repository
public class M00TaskJpaStore implements M00TaskStore {
    private final M00TaskRepository repository;

    public M00TaskJpaStore(M00TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public M00Task retrieve(M00TaskId id) {
        TaskSpecification taskSpecification = new TaskSpecification<M00TaskJpo>();
        if (StringUtil.isNotBlank(id.getProjectNumber())) {
            taskSpecification.add(new SearchCriteria(PROJECT_NUMBER.getFieldName(), id.getProjectNumber(), SearchOperation.EQUAL));
        }
        if (StringUtil.isNotBlank(id.getTaskName())) {
            taskSpecification.add(new SearchCriteria(TASK_NAME.getFieldName(), id.getTaskName(), SearchOperation.EQUAL));
        }
        Optional<M00TaskJpo> results = this.repository.findOne(taskSpecification);
        return results.isPresent()? results.get().toDomain() : null;
    }

    @Override
    public M00Task update(M00Task entity) {
        M00TaskJpo jpoToUpdate = new M00TaskJpo(entity);
        M00TaskJpo updatedJpo = this.repository.save(jpoToUpdate);
        return updatedJpo.toDomain();
    }

    @Override
    public M00Task create(M00Task entity) {
        return this.repository.save(new M00TaskJpo(entity)).toDomain();
    }


    @Override
    public void delete(M00TaskId id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<M00Task> retrieveAll(String projectNumber) {
        return this.repository.findAllByProjectNumberContains(projectNumber).stream().map(M00TaskJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<M00Task> findTaskByConditions(String projectNumber, String taskName, String planDate,
                                                String actualEndDate, String status, Pageable pageable) {
        TaskSpecification taskSpecification = new TaskSpecification<M00TaskJpo>();
        if (StringUtil.isNotBlank(projectNumber)) {
            taskSpecification.add(new SearchCriteria(PROJECT_NUMBER.getFieldName(), projectNumber, SearchOperation.MATCH));
        }
        if (StringUtil.isNotBlank(taskName)) {
            taskSpecification.add(new SearchCriteria(TASK_NAME.getFieldName(), taskName, SearchOperation.MATCH));
        }
        if (StringUtil.isNotBlank(status)) {
            taskSpecification.add(new SearchCriteria(STATUS.getFieldName(), status, SearchOperation.MATCH));
        }
        //TODO:
        if (StringUtil.isNotBlank(planDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            formatter.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));

            Date date;
            LocalDate localdate;
            try {
                date = DateUtils.parseDateStrictly(planDate, new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "dd/MM-yyyy"});
                localdate = LocalDate.parse(planDate);
                DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Use the format() method to convert LocalDate to string
                String dateToString = localdate.format(formatterD);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            taskSpecification.add(new SearchCriteria(PLAN_DATE.getFieldName(), localdate, SearchOperation.LESS_THAN));
        }
        if (StringUtil.isNotBlank(actualEndDate)) {
            taskSpecification.add(new SearchCriteria(ACTUAL_END_DATE.getFieldName(), actualEndDate, SearchOperation.LESS_THAN));
        }

        Page<M00TaskJpo> results = this.repository.findAll(taskSpecification, pageable);
        return results.getContent().stream().map(M00TaskJpo::toDomain).collect(Collectors.toList());
    }
}
