package com.example.bots_crew.service;

import com.example.bots_crew.model.Department;
import com.example.bots_crew.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void testGetDepartmentByName_WhenDepartmentExist() {
        // Given
        String departmentName = "Computer Science";
        String head = "Shevchenko";
        Department department = new Department();
        department.setHead(head);
        department.setName(departmentName);

        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department));

        // When
        Optional<Department> result = departmentService.getDepartmentByName(departmentName);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Shevchenko", result.get().getHead());
    }

    @Test
    public void testGetDepartmentByName_WhenDepartmentNotExist() {
        // Given
        String departmentName = "Computer Science";

        when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

        // When
        Optional<Department> result = departmentService.getDepartmentByName(departmentName);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAverageSalaryForDepartment() {
        // Given
        String departmentName = "Computer Science";

        when(departmentRepository.getAverageSalaryForDepartment(departmentName)).thenReturn(BigDecimal.ONE);

        // When
        BigDecimal result = departmentService.getAverageSalaryForDepartment(departmentName);

        // Then
        assertEquals(BigDecimal.ONE, result);
    }
}
