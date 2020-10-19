package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.Employer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployersService {

    public List<Employer> getAll();
    
    public String getByName(String name);
    
    public Employer saveOne(Employer e);

    public void deleteOne(long id);
    
    public void updateOne(Employer e);
    
    public List<Employer> findByDistrict(String district_name);
    
    public List<Employer> findBetweenDate(String data_start, String data_end);
}
