package com.spring.boot.repo;

import com.spring.boot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.id IN :idList")
    List<Employee> findEmployeeDtoByListOfId(@Param("idList") List<Long> idList);

    Optional<Employee> findByName(String Name);
    @Query("SELECT e from  Employee e where e.name in :nameList")
    List<Employee> findByNameList(List<String> nameList);
}
