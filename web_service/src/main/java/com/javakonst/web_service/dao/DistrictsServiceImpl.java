package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.District;
import com.javakonst.web_service.services.DistrictsRepository;
import com.javakonst.web_service.services.EmployersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictsServiceImpl implements DistrictsService {
    
    private DistrictsRepository districtsRepo;
    private EmployersRepository employersRepo;
    
    @Override
    public District saveOne(District d) {

        return districtsRepo.saveAndFlush(d);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        districtsRepo.deleteByName(name);
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
        return districtsRepo.findAll();
    }
    
    @Override
    public List<Object> getWithAvgSalary() {
        List<Object> districtsList = districtsRepo.getWithAvgSalary();
        return districtsList;
    }

    @Override
    public District getById(long id){
        return districtsRepo.getOne(id);
    }

    @Override
    public District getByName(String name){
        return districtsRepo.getByName(name);
    }
    
}
