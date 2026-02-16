package com.sprk.employee_management.mapper;

import com.sprk.employee_management.dto.EmployeeDto;
import com.sprk.employee_management.dto.EmployeeFileDto;
import com.sprk.employee_management.entity.EmployeeInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto mapEmployeeInfotoEmployeeDto(EmployeeInfo employeeInfo);
    EmployeeInfo mapEmployeeDtotoEmployeeInfo(EmployeeDto employeeDto);
    EmployeeInfo mapEmployeeDtotoEmployeeFileDto(EmployeeFileDto employeeFileDto);
    EmployeeFileDto mapEmployeeFileDtotoEmployeeDto(EmployeeInfo employeeInfo);
    EmployeeInfo mapEmployeeFileDtotoEmployeeInfo(EmployeeFileDto employeeFileDto);
}