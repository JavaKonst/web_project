package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployersRepository extends JpaRepository<Employer, Long> {
    
    Employer getEmployerByName(String name);
    
    List<Employer> getEmployersByDistrict_Name(String district_name);
    
    List<Employer> findEmployerByBirthdayBetween(LocalDate afterData, LocalDate beforeData);

    @Transactional
    @Modifying
    @Query("delete from Employer e where e.id=:id")
    void deleteById(@Param("id") long id);

    List<Employer> getEmployersByDistrictName(String name);

    Employer getEmployerById(long id);

}

