package com.javakonst.web_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


@Entity
@Table(name = "districts")
@Data
public class District {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;

    @OneToMany(mappedBy = "district", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Employer> employers = new ArrayList<>();
    
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

    public void addEmployer(Employer employer){
        this.employers.add(employer);
        employer.setDistrict(this);
    }
}

