package com.javakonst.web_service.services;

import com.javakonst.web_service.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DistrictsRepository extends JpaRepository<District, Long> {
    
    @Query(
            value = "SELECT d.name, AVG(e.salary) " +
                    "FROM districts d " +
                    "JOIN  employers e " +
                    "WHERE d.id = e.district_id " +
                    "GROUP BY d.name",
            nativeQuery = true)
    public List<Object> getWithAvgSalary();

    public District getByName(String name);

}

