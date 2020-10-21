package com.javakonst.web_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employers")
@Data
public class Employer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district;
    
    @Column
    private String name;
    
    @Column
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

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate getBirthday() {
        return birthday;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

