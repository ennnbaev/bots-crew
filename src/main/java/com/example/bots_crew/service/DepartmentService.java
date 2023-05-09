package com.example.bots_crew.service;

import com.example.bots_crew.model.Department;
import com.example.bots_crew.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public record DepartmentService(DepartmentRepository departmentRepository) {

    public Optional<Department> getDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName);
    }

    public BigDecimal getAverageSalaryForDepartment(String name) {
        return departmentRepository.getAverageSalaryForDepartment(name);
    }
}
