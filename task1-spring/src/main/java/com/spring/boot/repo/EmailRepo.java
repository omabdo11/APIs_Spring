package com.spring.boot.repo;

import com.spring.boot.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmailRepo extends JpaRepository<Email, Long> {


    Optional<Email> findByType(String type);
    @Query("select e from Email e where e.type in :type")
    List<Email> findByTypeEmails(@Param("type") String type);

    Optional<Email> findByValue(String value);
    @Query("select e from Email e where e.value in :value")
    List<Email> findByValueEmails(@Param("value") String value);

    @Query("select e from Email e where e.type in :listType")
    List<Email> findByListOfTypeEmails(@Param("listType") List<String> listType);


}
