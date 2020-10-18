package com.javakonst.web_service.services;

import com.javakonst.web_service.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployersRepository extends JpaRepository<Employer, Long> {
    
    Employer getEmployerByName(String name);
    
    List<Employer> getEmployersByDistrict_Name(String district_name);
    
    List<Employer> findEmployerByBirthdayBetween(LocalDate afterData, LocalDate beforeData);
    
}

