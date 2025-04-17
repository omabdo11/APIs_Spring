package com.spring.boot.repo;


import com.spring.boot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByUserName(String userName);

    List<Account> findByUserNameContains(String userName);

    @Query(value = "SELECT a from Account a where a.phoneNumber like concat(concat('%',:phone),'%' ) " )
    List<Account> findByAccountPhone(@Param("phone") String phone);
}
