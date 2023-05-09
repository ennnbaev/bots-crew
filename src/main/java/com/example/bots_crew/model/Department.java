package com.example.bots_crew.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "head_of_department", nullable = false)
    private String head;

    @OneToMany(mappedBy = "department")
    private List<Lector> lectors;
}
