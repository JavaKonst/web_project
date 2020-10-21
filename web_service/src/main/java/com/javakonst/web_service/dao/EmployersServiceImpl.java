package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.District;
import com.javakonst.web_service.entity.Employer;
import com.javakonst.web_service.services.DistrictsRepository;
import com.javakonst.web_service.services.EmployersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployersServiceImpl implements EmployersService {
    
    private DistrictsRepository districtsRepo;
    private EmployersRepository employersRepo;
    
    @Override
    public Employer saveOne(Employer e) {
        District newDistrict = districtsRepo.getByName(e.getDistrict().getName());
        if (newDistrict!=null){
            newDistrict.addEmployer(e);
            districtsRepo.save(newDistrict);
            return e;
        }
        return null;
    }

    @Override
    public void deleteOne(long id) {
        employersRepo.deleteById(id);
    }
    
    @Override
    public void updateOne(Employer e) {
        Employer one = employersRepo.getOne(e.getId());
        one.setName(e.getName());
        one.setSalary(e.getSalary());
        one.setBirthday(e.getBirthday());
        one.setDistrict(e.getDistrict());
        employersRepo.save(one);
    }
    
    @Override
    public List<Employer> getAll() {
        return employersRepo.findAll();
    }
    
    @Override
    public String getByName(String name) {
        Employer empl = employersRepo.getEmployerByName(name);
        return empl.toString();
    }
    
    @Override
    public List<Employer> findByDistrict(String district_name) {
        return employersRepo.getEmployersByDistrict_Name(district_name);
    }
    
    @Override
    public List<Employer> findBetweenDate(String data_start, String data_end) {
        String datePattern = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
        LocalDate dateStart = LocalDate.parse(data_start, dtf);
        LocalDate dateEnd = LocalDate.parse(data_end, dtf);
        
        return employersRepo.findEmployerByBirthdayBetween(dateStart, dateEnd);
    }

    @Override
    public List<Employer> getByDistrictName(String name) {
        return employersRepo.getEmployersByDistrictName(name);
    }

    @Override
    public Employer getOneById(long id){
        return employersRepo.getEmployerById(id);
    }

}
