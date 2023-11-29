package com.poscodx.odc.ampro015.service.logic;

import com.poscdx.odc.ampro015.domain.logic.TaskLogic;
import com.poscdx.odc.ampro015.domain.store.TaskStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TaskSpringLogic extends TaskLogic {
    public TaskSpringLogic(TaskStore store) {
        super(store);
    }
}
