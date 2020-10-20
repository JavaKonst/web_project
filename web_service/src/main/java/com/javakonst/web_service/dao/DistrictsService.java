package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.District;

import java.util.List;

public interface DistrictsService {

    public District getById(long id);

    public District getByName(String name);

    public District saveOne(District d);
    
    public void deleteByName(String name);
    
    public void updateOne(District d);
    
    public List<District> getAll();
    
    public List<Object> getWithAvgSalary();
    
}
