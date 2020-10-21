package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.Employer;

import java.util.List;

public interface EmployersService {

    List<Employer> getAll();
    
    Employer saveOne(Employer e);

    void deleteOne(long id);
    
    void updateOne(Employer e);
    
    List<Employer> findByDistrict(String district_name);
    
    List<Employer> findBetweenDate(String data_start, String data_end);

    List<Employer> getByDistrictName(String name);

    Employer getOneById(long id);
}
