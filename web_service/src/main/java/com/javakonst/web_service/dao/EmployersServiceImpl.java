package com.javakonst.web_service.dao;

import com.javakonst.web_service.entity.Employer;
import com.javakonst.web_service.services.DistrictsRepository;
import com.javakonst.web_service.services.EmployersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployersServiceImpl implements EmployersService {
    
    private DistrictsRepository districtsRepo;
    private EmployersRepository employersRepo;
    
    @Override
    public Employer saveOne(Employer e) {
        return employersRepo.save(e);
    }
    
    @Override
    public void deleteOne(long id) {
        employersRepo.deleteById(id);
    }
    
    @Override
    public void updateOne(Employer e) {
        Employer one = employersRepo.getOne(e.getId());
        one.setName(e.getName());
        one.setBirthday(e.getBirthday());
        one.setSalary(e.getSalary());
        one.setDistrict(e.getDistrict());
    }
    
    //TODO: подумать нужно ли преобразование
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
//        List<Employer> employerList = employersRepo.getEmployersByDistrict_Name(district_name);
//        List<String> list = new ArrayList<>();
//
//        for (Employer employer : employerList) {
//            list.add(employer.toString());
//        }
//
//        return list;
    
        return employersRepo.getEmployersByDistrict_Name(district_name);
    }
    
    @Override
    public List<Employer> findBetweenDate(String data_start, String data_end) {
        String datePattern = "dd.MM.yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
        LocalDate dateStart = LocalDate.parse(data_start, dtf);
        LocalDate dateEnd = LocalDate.parse(data_end, dtf);
        
        return employersRepo.findEmployerByBirthdayBetween(dateStart, dateEnd);
    }
    
}
