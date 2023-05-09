package com.example.bots_crew.service;

import com.example.bots_crew.model.Lector;
import com.example.bots_crew.repository.LectorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record LectorService(LectorRepository lectorRepository) {

    public Long countByDepartmentNameAndDegree(String name, String degree) {
        return lectorRepository.countByDepartmentNameAndDegreeName(name, degree);
    }

    public Long countByDepartmentName(String departmentName) {
        return lectorRepository.countByDepartmentName(departmentName);
    }

    public List<String> getLectorsNameByTemplate(String template) {
        List<Lector> lectors = lectorRepository.findAll();
        List<String> matches = new ArrayList<>();
        for (Lector lector : lectors) {
            if (lector.getName().toLowerCase().contains(template.toLowerCase())) {
                matches.add(lector.getName());
            }
        }
        return matches;
    }
}
