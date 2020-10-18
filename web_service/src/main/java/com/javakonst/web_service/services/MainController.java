package com.javakonst.web_service.services;

import com.javakonst.web_service.dao.DistrictsService;
import com.javakonst.web_service.dao.EmployersService;
import com.javakonst.web_service.entity.District;
import com.javakonst.web_service.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Random;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Log
public class MainController {
    
    private EmployersService employersService;
    private DistrictsService districtsService;
    
    @PostConstruct
    private void init() {
        log.info("Заполнение БД...");
        
        List<District> districtList = new ArrayList<>();
        districtList.add(new District("НИИЦ"));
        districtList.add(new District("КТЦ"));
        districtList.add(new District("Отдел продаж"));
        districtList.add(new District("Склад"));
        districtList.add(new District("Отдел снабжения"));
        
        List<Employer> employerList = new ArrayList<>();
        employerList.add(new Employer("ivan", 30000, districtList.get(0)));
        employerList.add(new Employer("john", 33000, districtList.get(0)));
        employerList.add(new Employer("oleg", 24000, districtList.get(0)));
        employerList.add(new Employer("sergey", 41000, districtList.get(0)));
        employerList.add(new Employer("kostya", 60000, districtList.get(0)));
        
        employerList.add(new Employer("anton", 25000, districtList.get(1)));
        employerList.add(new Employer("olya", 20000, districtList.get(1)));
        employerList.add(new Employer("irina", 27000, districtList.get(1)));
        employerList.add(new Employer("marina", 22000, districtList.get(1)));
        employerList.add(new Employer("lena", 32000, districtList.get(1)));
        
        employerList.add(new Employer("andrey", 22000, districtList.get(2)));
        employerList.add(new Employer("alex", 25000, districtList.get(2)));
        employerList.add(new Employer("katya", 30000, districtList.get(2)));
        
        employerList.add(new Employer("olecy", 15000, districtList.get(3)));
        employerList.add(new Employer("dima", 17000, districtList.get(3)));
        
        employerList.add(new Employer("artem", 23000, districtList.get(4)));
        employerList.add(new Employer("yula", 25000, districtList.get(4)));
        employerList.add(new Employer("nastya", 23000, districtList.get(4)));
        employerList.add(new Employer("anna", 30000, districtList.get(4)));
        
        Random random = new Random();
        for (Employer employer : employerList) {
            int year = 2000 - random.nextInt(50);
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(28) + 1;
            employer.setBirthday(LocalDate.of(year, month, day));
        }
        
        for (District district : districtList) {
            districtsService.saveOne(district);
        }
        
        for (Employer employer : employerList) {
            employersService.saveOne(employer);
        }
        
        log.info("БД заполнена!");
    }
    
    @GetMapping("/all_d_with_s")
    public List<Object> getDistrictsWithAvgSamary() {
        return districtsService.getWithAvgSalary();
    }
    
    @GetMapping(value = "/all_e{district_name}")
    public List<Employer> getEmployersByDistricts(@RequestParam(defaultValue = "all") String district_name, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (district_name.equals("all")) return employersService.getAll();
        return employersService.findByDistrict(district_name);
    }
    
    @GetMapping("/search{ds}{de}")
    public List<Employer> getEmployersBetweenDate(@RequestParam String ds, @RequestParam String de) {
        return employersService.findBetweenDate(ds, de);
    }
    
    @DeleteMapping("/e_delete{id}")
    public void deleteEmployerById(@RequestParam String id) {
        if (id.isEmpty()) return;
        employersService.deleteOne(Long.parseLong(id));
    }
    
    @DeleteMapping("/d_delete{id}")
    public void deleteDistrictById(@RequestParam String id) {
        if (id.isEmpty()) return;
        districtsService.deleteOne(Long.parseLong(id));
    }
    
    @PostMapping("/e_update")
    public void updateEmployer(@RequestBody Employer employer) {
        employersService.updateOne(employer);
    }
    
    @PostMapping("/d_update")
    public void updateDistrict(@RequestBody District district) {
        districtsService.updateOne(district);
    }
    
    @PostMapping("/e_create")
    public void createEmployer(@RequestBody Employer employer, HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Content-Type", "application/json");
        employersService.saveOne(employer);
    }
    
    @PostMapping("/d_create")
    public void createDistrict(@RequestBody District district, HttpServletResponse response) {
        districtsService.saveOne(district);
    }
    
    @GetMapping("/all_d")
    public List<District> getall(){
        return districtsService.getAll();
    }
}
