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
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class WebServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
    
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

    @Bean
    public CommandLineRunner run(DistrictsService districtsService, EmployersService employersService) throws Exception {
        return args -> {

            //TODO:применить сюда паттерн Билдер
            System.out.println("Заполнение БД...");

            List<Employer> employerList1 = new ArrayList<>();
            List<Employer> employerList2 = new ArrayList<>();
            List<Employer> employerList3 = new ArrayList<>();

            employerList1.add(new Employer("anton", 25000));
            employerList1.add(new Employer("andrey", 22000));
            employerList1.add(new Employer("alex", 25000));
            employerList1.add(new Employer("katya", 30000));

            employerList2.add(new Employer("sergey", 41000));
            employerList2.add(new Employer("olecy", 15000));
            employerList2.add(new Employer("dima", 17000));

            employerList3.add(new Employer("artem", 23000));
            employerList3.add(new Employer("yula", 25000));
            employerList3.add(new Employer("nastya", 23000));
            employerList3.add(new Employer("anna", 30000));

            Random random = new Random();
            for (Employer employer : employerList1) {
                int year = 2000 - random.nextInt(50);
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1;
                employer.setBirthday(LocalDate.of(year, month, day));
            }

            for (Employer employer : employerList2) {
                int year = 2000 - random.nextInt(50);
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1;
                employer.setBirthday(LocalDate.of(year, month, day));
            }

            for (Employer employer : employerList3) {
                int year = 2000 - random.nextInt(50);
                int month = random.nextInt(12) + 1;
                int day = random.nextInt(28) + 1;
                employer.setBirthday(LocalDate.of(year, month, day));
            }

            District d1 = new District("Отдел продаж");
            for (Employer employer : employerList1) {
                d1.addEmployer(employer);
            }

            District d2 = new District("Склад");
            for (Employer employer : employerList2) {
                d2.addEmployer(employer);
            }

            District d3 = new District("Отдел снабжения");
            for (Employer employer : employerList3) {
                d3.addEmployer(employer);
            }

            districtsService.saveOne(d1);
            districtsService.saveOne(d2);
            districtsService.saveOne(d3);

            System.out.println("БД заполнена!");
        };
    }
    
}
