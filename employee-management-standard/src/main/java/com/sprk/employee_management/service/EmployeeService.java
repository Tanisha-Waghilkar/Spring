package com.sprk.employee_management.service;

import com.sprk.employee_management.dto.EmployeeDto;
import com.sprk.employee_management.dto.EmployeeFileDto;
import com.sprk.employee_management.entity.EmployeeInfo;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeFileDto employeeFileDto)throws IOException;

//    List<EmployeeInfo> getallEmployeeBy();

    List<EmployeeDto> getallEmployee();
    //
    EmployeeDto getempBYId(String empIdStr);
    //
    EmployeeDto deleteById(String empIdStr);
    //
    EmployeeDto updateById(String empIdStr, EmployeeDto updatedemployeeDto);

    Resource getempByfileName(String fileName) throws IOException;
}