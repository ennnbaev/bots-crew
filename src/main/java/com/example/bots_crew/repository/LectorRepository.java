package com.example.bots_crew.repository;


import com.example.bots_crew.model.Lector;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface LectorRepository extends JpaRepository<Lector, Long> {
    Long countByDepartmentName(String departmentName);

    Long countByDepartmentNameAndDegreeName(String name, String degree);
}
