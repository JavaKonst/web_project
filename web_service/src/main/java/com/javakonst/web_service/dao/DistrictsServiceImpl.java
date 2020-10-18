package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.District;
import com.javakonst.web_service.services.DistrictsRepository;
import com.javakonst.web_service.services.EmployersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DistrictsServiceImpl implements DistrictsService {
    
    private DistrictsRepository districtsRepo;
    private EmployersRepository employersRepo;
    
    @Override
    public District saveOne(District d) {
        return districtsRepo.save(d);
    }
    
    @Override
    public void deleteOne(long id) {
        districtsRepo.deleteById(id);
    }
    
    @Override
    public void updateOne(District d) {
        District one = districtsRepo.getOne(d.getId());
        one.setName(d.getName());
        one.setEmployers(d.getEmployers());
        districtsRepo.flush();
    }
    
    @Override
    public List<District> getAll() {
//        List<District> all = ;
//        List<String> list = new ArrayList<>();
//
//        for (District district : all) {
//            list.add(district.toString());
//        }
//
        return districtsRepo.findAll();
    }
    
    @Override
    public List<Object> getWithAvgSalary() {
        List<Object> districtsList = districtsRepo.getWithAvgSalary();
        return districtsList;
    }
    
}
