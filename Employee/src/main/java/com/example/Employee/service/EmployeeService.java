package com.example.Employee.service;

import com.example.Employee.dto.DeleteEmployeeDto;
import com.example.Employee.dto.EmployeeDataForTable;
import com.example.Employee.dto.EmployeeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    private RestTemplate restTemplate;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(RestTemplate restTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.restTemplate = restTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void sandEmployeeData(EmployeeDto employeeDto){

        String url = "http://localhost:8080/employee/create";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EmployeeDto> request = new HttpEntity<>(employeeDto,headers);

        EmployeeDto odgovor = restTemplate.postForObject(url, request, EmployeeDto.class);

        System.out.println("Server ke odgovorio: "+odgovor.getFirstName());

    }

    public void sendDeleteEmployee( DeleteEmployeeDto deleteEmployeeDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DeleteEmployeeDto> request = new HttpEntity<>(deleteEmployeeDto,headers);

        String url = "http://localhost:8080/employee/delete";
        restTemplate.exchange(url, HttpMethod.DELETE,
                request, Void.class);

    }

    public List<EmployeeDataForTable> getAllEmployees(){
        String url = "http://localhost:8080/employee/get-all";
        ResponseEntity<EmployeeDataForTable[]> response = restTemplate.getForEntity(url,EmployeeDataForTable[].class);
        return Arrays.asList(response.getBody());
    }

    public void giveEmployeeUsername(@Valid EmployeeDto employeeDto) {
        employeeDto.setUsername(employeeDto.getFirstName()+"("+employeeDto.getRole());
    }

    public void encodePasswordAndEmptyConfirm(EmployeeDto employeeDto) {
        String rowPass = employeeDto.getPassword();
        employeeDto.setPassword(bCryptPasswordEncoder.encode(rowPass));
        employeeDto.setConfirmPassword("");
    }
}
