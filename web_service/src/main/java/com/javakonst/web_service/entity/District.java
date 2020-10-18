package com.javakonst.web_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static java.util.Objects.isNull;

@Entity
@Table(name = "districts")
@Data
public class District {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String name;
    
    @OneToMany(mappedBy = "district", orphanRemoval = true)
    @JsonIgnore
    private List<Employer> employers;
    
    @Override
    public String toString() {
        int size;
        if (isNull(employers)) size = 0;
        else size = employers.size();
        return "District{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employers=" + size +
                '}';
    }
    
    public District(String name) {
        super();
        this.name = name;
    }
    
    public District() {
    }
}

