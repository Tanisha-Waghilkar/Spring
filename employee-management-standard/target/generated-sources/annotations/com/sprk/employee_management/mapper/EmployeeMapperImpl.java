package com.sprk.employee_management.mapper;

import com.sprk.employee_management.dto.EmployeeDto;
import com.sprk.employee_management.dto.EmployeeFileDto;
import com.sprk.employee_management.entity.EmployeeInfo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T19:48:09+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto mapEmployeeInfotoEmployeeDto(EmployeeInfo employeeInfo) {
        if ( employeeInfo == null ) {
            return null;
        }

        EmployeeDto.EmployeeDtoBuilder employeeDto = EmployeeDto.builder();

        employeeDto.empId( employeeInfo.getEmpId() );
        employeeDto.firstName( employeeInfo.getFirstName() );
        employeeDto.lastName( employeeInfo.getLastName() );
        employeeDto.email( employeeInfo.getEmail() );
        employeeDto.phone( employeeInfo.getPhone() );
        employeeDto.gender( employeeInfo.getGender() );
        employeeDto.age( employeeInfo.getAge() );
        employeeDto.salary( employeeInfo.getSalary() );
        employeeDto.department( employeeInfo.getDepartment() );
        employeeDto.fileName( employeeInfo.getFileName() );
        employeeDto.filePath( employeeInfo.getFilePath() );

        return employeeDto.build();
    }

    @Override
    public EmployeeInfo mapEmployeeDtotoEmployeeInfo(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        EmployeeInfo.EmployeeInfoBuilder employeeInfo = EmployeeInfo.builder();

        employeeInfo.empId( employeeDto.getEmpId() );
        employeeInfo.firstName( employeeDto.getFirstName() );
        employeeInfo.lastName( employeeDto.getLastName() );
        employeeInfo.email( employeeDto.getEmail() );
        employeeInfo.phone( employeeDto.getPhone() );
        employeeInfo.gender( employeeDto.getGender() );
        if ( employeeDto.getAge() != null ) {
            employeeInfo.age( employeeDto.getAge() );
        }
        if ( employeeDto.getSalary() != null ) {
            employeeInfo.salary( employeeDto.getSalary() );
        }
        employeeInfo.department( employeeDto.getDepartment() );
        employeeInfo.fileName( employeeDto.getFileName() );

        return employeeInfo.build();
    }

    @Override
    public EmployeeInfo mapEmployeeDtotoEmployeeFileDto(EmployeeFileDto employeeFileDto) {
        if ( employeeFileDto == null ) {
            return null;
        }

        EmployeeInfo.EmployeeInfoBuilder employeeInfo = EmployeeInfo.builder();

        employeeInfo.firstName( employeeFileDto.getFirstName() );
        employeeInfo.lastName( employeeFileDto.getLastName() );
        employeeInfo.email( employeeFileDto.getEmail() );
        employeeInfo.phone( employeeFileDto.getPhone() );
        employeeInfo.gender( employeeFileDto.getGender() );
        if ( employeeFileDto.getAge() != null ) {
            employeeInfo.age( employeeFileDto.getAge() );
        }
        if ( employeeFileDto.getSalary() != null ) {
            employeeInfo.salary( employeeFileDto.getSalary() );
        }
        employeeInfo.department( employeeFileDto.getDepartment() );

        return employeeInfo.build();
    }

    @Override
    public EmployeeFileDto mapEmployeeFileDtotoEmployeeDto(EmployeeInfo employeeInfo) {
        if ( employeeInfo == null ) {
            return null;
        }

        EmployeeFileDto.EmployeeFileDtoBuilder employeeFileDto = EmployeeFileDto.builder();

        employeeFileDto.firstName( employeeInfo.getFirstName() );
        employeeFileDto.lastName( employeeInfo.getLastName() );
        employeeFileDto.email( employeeInfo.getEmail() );
        employeeFileDto.phone( employeeInfo.getPhone() );
        employeeFileDto.gender( employeeInfo.getGender() );
        employeeFileDto.age( employeeInfo.getAge() );
        employeeFileDto.salary( employeeInfo.getSalary() );
        employeeFileDto.department( employeeInfo.getDepartment() );

        return employeeFileDto.build();
    }

    @Override
    public EmployeeInfo mapEmployeeFileDtotoEmployeeInfo(EmployeeFileDto employeeFileDto) {
        if ( employeeFileDto == null ) {
            return null;
        }

        EmployeeInfo.EmployeeInfoBuilder employeeInfo = EmployeeInfo.builder();

        employeeInfo.firstName( employeeFileDto.getFirstName() );
        employeeInfo.lastName( employeeFileDto.getLastName() );
        employeeInfo.email( employeeFileDto.getEmail() );
        employeeInfo.phone( employeeFileDto.getPhone() );
        employeeInfo.gender( employeeFileDto.getGender() );
        if ( employeeFileDto.getAge() != null ) {
            employeeInfo.age( employeeFileDto.getAge() );
        }
        if ( employeeFileDto.getSalary() != null ) {
            employeeInfo.salary( employeeFileDto.getSalary() );
        }
        employeeInfo.department( employeeFileDto.getDepartment() );

        return employeeInfo.build();
    }
}
