package com.example.bots_crew.repository;

import com.example.bots_crew.model.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);

    @Query("SELECT AVG(l.salary) FROM Department d JOIN d.lectors l WHERE d.name = :name")
    BigDecimal getAverageSalaryForDepartment(@Param("name") String name);
}
