package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.IssueManagementJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueManagementRepository extends JpaRepository<IssueManagementJpo, Integer> {
}
