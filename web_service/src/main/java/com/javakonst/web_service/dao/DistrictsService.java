package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.District;

import java.util.List;

public interface DistrictsService {

    District getByName(String name);

    District saveOne(District d);
    
    void deleteByName(String name);
    
    void updateOne(District d);
    
    List<District> getAll();
    
    List<Object> getWithAvgSalary();
    
}
