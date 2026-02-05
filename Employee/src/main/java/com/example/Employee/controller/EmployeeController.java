package com.example.Employee.controller;

import com.example.Employee.service.EmployeeService;
import com.example.Employee.dto.DeleteEmployeeDto;
import com.example.Employee.dto.EmployeeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public EmployeeController(EmployeeService employeeService, BCryptPasswordEncoder encoder) {
        this.employeeService = employeeService;
        this.encoder = encoder;
    }


    @GetMapping("/register")
    public String addEmployee(Model model){
        model.addAttribute("employeeDto", new EmployeeDto());
        model.addAttribute("passwordNotMatch", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("employeeDto")EmployeeDto employeeDto, BindingResult bindingResult, Model model){

//
        employeeService.giveEmployeeUsername(employeeDto);

        if (bindingResult.hasErrors()){
            return "register";
        }

        if (!employeeDto.getPassword().equals(employeeDto.getConfirmPassword())){
            model.addAttribute("passwordNotMatch", true);
            return "register";
        }

//        String rowPass = employeeDto.getPassword();
//        employeeDto.setPassword(encoder.encode(rowPass));
//        employeeDto.setConfirmPassword("");
        employeeService.encodePasswordAndEmptyConfirm(employeeDto);

        employeeService.sandEmployeeData(employeeDto);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String showDeleteView(Model model){
        model.addAttribute("deleteEmployeeDto",new DeleteEmployeeDto());

        return "delete-employee";
    }

    @PostMapping("/delete")
    public String sendDeletePost(@Valid @ModelAttribute("deleteEmployeeDto")DeleteEmployeeDto deleteEmployeeDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "delete-employee";
        }

        employeeService.sendDeleteEmployee(deleteEmployeeDto);
        model.addAttribute("successMessage","Employee Deleted Successfully!");

        model.addAttribute("deleteEmployeeDto", new DeleteEmployeeDto());
        return "delete-employee.html";
    }
}
