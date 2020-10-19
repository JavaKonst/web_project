package com.javakonst.web_service.services;

import com.javakonst.web_service.dao.DistrictsService;
import com.javakonst.web_service.dao.EmployersService;
import com.javakonst.web_service.entity.District;
import com.javakonst.web_service.entity.Employer;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/")
@AllArgsConstructor
@Log
public class MainController {
    
    private EmployersService employersService;
    private DistrictsService districtsService;

    @GetMapping("/all_d_with_s")
    public List<Object> getDistrictsWithAvgSamary() {
        return districtsService.getWithAvgSalary();
    }

    @GetMapping(value = "/all_e{district_name}")
    public List<Employer> getEmployersByDistricts(@RequestParam(defaultValue = "all") String district_name, HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
        if (district_name.equals("all")) return employersService.getAll();
        return employersService.findByDistrict(district_name);
    }

    @GetMapping("/search{ds}{de}")
    public List<Employer> getEmployersBetweenDate(@RequestParam String ds, @RequestParam String de) {
        return employersService.findBetweenDate(ds, de);
    }

    @DeleteMapping("/e_delete{id}")
    public void deleteEmployerById(@RequestParam(defaultValue = "0") long id) {
        if (id==0) return;
        employersService.deleteOne(id);
    }

    @DeleteMapping("/d_delete{id}")
    public void deleteDistrictById(@RequestParam(defaultValue = "0") long id) {
        if (id==0) return;
        districtsService.deleteOne(id);
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
    public void createEmployer(@RequestBody Employer employer) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Content-Type", "application/json");
//        log.info(request.getContextPath());
        Employer employer1 = employersService.saveOne(employer);
        System.out.println(employer1.toString());

    }

    @PostMapping("/d_create")
    public void createDistrict(@RequestBody District district) {
        districtsService.saveOne(district);
    }

    @GetMapping("/all_d")
    public List<District> getall(){
        return districtsService.getAll();
    }
}
