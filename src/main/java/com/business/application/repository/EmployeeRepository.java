package com.business.application.repository;

import com.business.application.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "SELECT * FROM Employee e WHERE e.contractNumber = :contractNumber", nativeQuery = true)
    Optional<Employee> findEmployeeByContractNumber(@Param("contractNumber") String contractNumber);

    Optional<Employee> findByUserId(String id);
}
