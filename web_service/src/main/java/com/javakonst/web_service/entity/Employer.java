package com.javakonst.web_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "employers")
@Data
public class Employer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id")
    private District district;
    
    @Column
    private String name;
    
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    
    @Column
    private int salary;
    
    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", district=" + district.getName() +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
    
    public Employer(String name, int salary) {
        super();
        this.name = name;
        this.salary = salary;
    }
    
    public Employer() {
    }
}

