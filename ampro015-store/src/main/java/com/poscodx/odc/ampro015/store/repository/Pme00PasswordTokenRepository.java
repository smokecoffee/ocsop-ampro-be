package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00PasswordTokenJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Pme00PasswordTokenRepository
    extends JpaRepository<Pme00PasswordTokenJpo, Integer> {

    Optional<Pme00PasswordTokenJpo> findById(int id);

    @Query(value = "SELECT ID, EMPLOYEE_ID, TOKEN, EXPIRE, CREATE_AT, CREATE_BY, UPDATE_AT, UPDATE_BY\n" +
            "FROM tb_pme00_reset_password where TOKEN=:token", nativeQuery = true)
    List<Object[]> FindPasswordTokenByToken(@Param("token") String token);
}

