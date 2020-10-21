package com.javakonst.web_service;

import com.javakonst.web_service.dao.DistrictsService;
import com.javakonst.web_service.dao.EmployersService;
import com.javakonst.web_service.entity.District;
import com.javakonst.web_service.entity.Employer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class WebServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner run(DistrictsService districtsService, EmployersService employersService) throws Exception {
        return args -> {
            System.out.println("Заполнение БД...");

            List<String> employerNames = Arrays.asList("anton", "andrey", "alex", "katya", "sergey", "olecy", "dima", "artem", "yula", "nastya");
            List<String> districtNames = Arrays.asList("Отдел продаж", "Склад", "Отдел снабжения");

            Random random = new Random();

            List<Employer> employers = new ArrayList<>();
            for (int i = 0; i < employerNames.size(); i++) {
                Employer employer = new Employer();
                employer.setName(employerNames.get(i));
                employer.setSalary(random.nextInt(10)*9000 + 10000);
                int year = 2000 - random.nextInt(50);
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1;
                employer.setBirthday(LocalDate.of(year, month, day));
                employers.add(employer);
            }

            List<District> districts = new ArrayList<>();
            for (int i = 0; i < districtNames.size(); i++) {
                districts.add(new District(districtNames.get(i)));
            }

            for (Employer employer : employers) {
                int districtId = random.nextInt(districts.size());
                districts.get(districtId).addEmployer(employer);
            }

            for (int i = 0; i < districts.size(); i++) {
                districtsService.saveOne(districts.get(i));
            }

            System.out.println("БД заполнена!");
        };
    }
    
}
