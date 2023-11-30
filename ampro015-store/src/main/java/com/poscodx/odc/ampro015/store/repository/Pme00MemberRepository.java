package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Pme00MemberRepository extends JpaRepository<Pme00MemberJpo, Integer> {

}
