package com.example.bots_crew.console;

import com.example.bots_crew.model.enums.DegreeEnum;
import com.example.bots_crew.service.DepartmentService;
import com.example.bots_crew.service.LectorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class UniversityConsole implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public UniversityConsole(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("Please enter a command or {break} to stop a program:");
            String input = scanner.nextLine().trim();

            if (input.startsWith("Who is head of department")) {
                String[] parts = input.split(" ");
                String departmentName = String.join(" ", Arrays.copyOfRange(parts, 5, parts.length));
                departmentService.getDepartmentByName(departmentName).ifPresentOrElse(
                        dep -> System.out.printf("Head of %s department is %s%n", dep.getName(), dep.getHead()),
                        () -> System.out.printf("Department %s not found or has no head%n", departmentName));

            } else if (input.startsWith("Show ") && input.endsWith(" statistics.")) {
                String[] parts = input.split(" ");
                String departmentName = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));
                if (departmentService.getDepartmentByName(departmentName).isPresent()) {
                    Long assistants = lectorService.countByDepartmentNameAndDegree(departmentName, DegreeEnum.ASSISTANT.name());
                    Long associates = lectorService.countByDepartmentNameAndDegree(departmentName, DegreeEnum.ASSOCIATE_PROFESSOR.name());
                    Long professors = lectorService.countByDepartmentNameAndDegree(departmentName, DegreeEnum.PROFESSOR.name());
                    System.out.printf("assistants - %d.%nassociate professors - %d.%nprofessors - %d.%n", assistants, associates, professors);
                } else {
                    System.out.printf("Department %s not found%n", departmentName);
                }
            } else if (input.startsWith("Show the average salary for the department")) {
                String[] parts = input.split(" ");
                String departmentName = String.join(" ", Arrays.copyOfRange(parts, 7, parts.length));
                departmentService.getDepartmentByName(departmentName).ifPresentOrElse(
                        department -> {
                            BigDecimal averageSalary = departmentService.getAverageSalaryForDepartment(departmentName);
                            System.out.printf("The average salary of %s department is %.2f%n", department.getName(), averageSalary);
                        },
                        () -> System.out.printf("Department %s not found%n", departmentName));
            } else if (input.startsWith("Show count of employee for")) {
                String[] parts = input.split(" ");
                String departmentName = String.join(" ", Arrays.copyOfRange(parts, 5, parts.length));
                departmentService.getDepartmentByName(departmentName).ifPresentOrElse(
                        department -> System.out.printf("%d%n", lectorService.countByDepartmentName(departmentName)),
                        () -> System.out.printf("Department %s not found%n", departmentName));
            } else if (input.startsWith("Global search by")) {
                String[] parts = input.split(" ");
                String template = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                System.out.println(String.join(", ", lectorService.getLectorsNameByTemplate(template)));
            } else if (input.equals("break")) {
                return;
            } else {
                System.out.println("Unknown command, please try again");
            }
        }
    }
}

