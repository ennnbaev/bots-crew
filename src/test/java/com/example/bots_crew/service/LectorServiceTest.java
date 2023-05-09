package com.example.bots_crew.service;

import com.example.bots_crew.model.Lector;
import com.example.bots_crew.model.enums.DegreeEnum;
import com.example.bots_crew.repository.LectorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LectorServiceTest {
    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorService lectorService;

    @Test
    public void testCountByDepartmentNameAndDegree() {
        // Given
        String departmentName = "Computer Science";
        Long count = 5L;

        when(lectorRepository.countByDepartmentNameAndDegreeName(departmentName, DegreeEnum.PROFESSOR.name())).thenReturn(count);

        // When
        Long result = lectorService.countByDepartmentNameAndDegree(departmentName, DegreeEnum.PROFESSOR.name());

        // Then
        assertEquals(count, result);
    }

    @Test
    public void testCountByDepartmentName() {
        // Given
        String departmentName = "Computer Science";
        Long count = 10L;

        when(lectorRepository.countByDepartmentName(departmentName)).thenReturn(count);

        // When
        Long result = lectorService.countByDepartmentName(departmentName);

        // Then
        assertEquals(count, result);
    }

    @Test
    public void testGetLectorsNameByTemplate() {
        // Given
        String template = "an";
        List<String> listResult = List.of("Svetlana", "Danil");
        Lector lectorDenis = new Lector();
        lectorDenis.setName("Denis");
        Lector lectorDanil = new Lector();
        lectorDanil.setName("Danil");
        Lector lectorSvetlana = new Lector();
        lectorSvetlana.setName("Svetlana");
        List<Lector> lectorList = List.of(lectorSvetlana, lectorDanil, lectorDenis);

        when(lectorRepository.findAll()).thenReturn(lectorList);

        // When
        List<String> result = lectorService.getLectorsNameByTemplate(template);

        // Then
        assertEquals(listResult.size(), result.size());
        assertEquals(listResult, result);
    }
}
