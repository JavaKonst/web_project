package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.District;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictsServiceImpl implements DistrictsService {
    
    private DistrictsRepository districtsRepo;

    @Override
    public District saveOne(District d) {
        District one = districtsRepo.getByName(d.getName());
        if (one == null) return districtsRepo.save(d);
        return null;
    }

    @Override
    public void deleteByName(String name) {
        districtsRepo.deleteByName(name);
    }
    
    @Override
    public void updateOne(District district) {
        District one = districtsRepo.getOne(district.getId());
        one.setName(district.getName());
        districtsRepo.save(one);
    }

    @Override
    public List<District> getAll() {
        return districtsRepo.findAll();
    }
    
    @Override
    public List<Object> getWithAvgSalary() {
        List<Object> districtsList = districtsRepo.getWithAvgSalary();
        return districtsList;
    }

    @Override
    public District getByName(String name){
        return districtsRepo.getByName(name);
    }
    
}
