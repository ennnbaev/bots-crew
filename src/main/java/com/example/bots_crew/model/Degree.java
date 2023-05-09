package com.example.bots_crew.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "degree")
    private List<Lector> lectors;

    @Override
    public String toString() {
        return "Degree{" +
                "name='" + name + '\'' +
                '}';
    }

}
