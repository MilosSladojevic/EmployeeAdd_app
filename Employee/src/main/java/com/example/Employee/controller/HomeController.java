package com.example.Employee.controller;

import com.example.Employee.service.EmployeeService;
import com.example.Employee.dto.EmployeeDataForTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private EmployeeService employeeService;

    @Autowired
    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/")
    public String showHome(Model model){
        List<EmployeeDataForTable>employees = employeeService.getAllEmployees();
        model.addAttribute("employees",employees);
        return "home";
    }




}
