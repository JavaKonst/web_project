package com.javakonst.web_service.services;

import com.javakonst.web_service.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictsRepository extends JpaRepository<District, Long> {
    
    @Query(
            value = "SELECT d.name, AVG(e.salary) " +
                    "FROM districts d " +
                    "JOIN  employers e " +
                    "WHERE d.id = e.district_id " +
                    "GROUP BY d.name",
            nativeQuery = true)
    public List<Object> getWithAvgSalary();
}

