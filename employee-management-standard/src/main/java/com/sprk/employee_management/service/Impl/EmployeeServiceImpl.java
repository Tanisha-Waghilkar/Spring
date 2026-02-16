package com.sprk.employee_management.service.Impl;

import com.sprk.employee_management.constant.EmployeeConstant;
import com.sprk.employee_management.dto.EmployeeDto;
import com.sprk.employee_management.dto.EmployeeFileDto;
import com.sprk.employee_management.entity.EmployeeInfo;
import com.sprk.employee_management.exception.EmailAlreadyExistsException;
import com.sprk.employee_management.exception.EmployeeIdInvalidException;
import com.sprk.employee_management.exception.EmployeeNotFoundException;
import com.sprk.employee_management.exception.PhoneAlreadyExistsException;
import com.sprk.employee_management.mapper.EmployeeMapper;
import com.sprk.employee_management.repository.EmployeeRepository;
import com.sprk.employee_management.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;
    @Value("${file.upload-dir}")
    private  String uploadDirectory;
    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeFileDto employeeFileDto)throws IOException {

        //already exits email and phone
        if (employeeRepository.existsByEmail(employeeFileDto.getEmail())) {
            throw new EmailAlreadyExistsException(String.format(EmployeeConstant.EMAIL_ALREADY_TAKEN, employeeFileDto.getEmail()), HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS));
        }


        if (employeeRepository.existsByPhone(employeeFileDto.getPhone())) {
            throw new PhoneAlreadyExistsException(String.format(EmployeeConstant.PHONE_ALREADY_TAKEN, employeeFileDto.getPhone()), HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS));
        }

        String fileName =  employeeFileDto.getFile().getOriginalFilename();


//        employeeDto.setEmpId(null);

        EmployeeInfo employeeInfo = employeeMapper.mapEmployeeFileDtotoEmployeeInfo(employeeFileDto);
        employeeInfo.setDepartment(employeeFileDto.getDepartment());
        employeeInfo.setFileName(fileName);
        employeeInfo.setFilePath(uploadDirectory+"/"+fileName);

        File dir = new File(uploadDirectory);

        File destination = new File(dir, fileName);
        employeeFileDto.getFile().transferTo(destination);

        EmployeeInfo savedEmployee = employeeRepository.save(employeeInfo);
        EmployeeDto savedEmployeeDto = employeeMapper.mapEmployeeInfotoEmployeeDto(employeeInfo);
        return savedEmployeeDto;
    }

    @Override
    public List<EmployeeDto> getallEmployee() {
        List<EmployeeInfo> allemp = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = allemp.stream()
                .map((employeeInfo) ->
                        employeeMapper.mapEmployeeInfotoEmployeeDto(employeeInfo)).toList();
        return employeeDtoList;
    }

    @Override
    public EmployeeDto getempBYId(String empIdStr) {

        if (!Pattern.matches("^\\d+$", empIdStr)) {
            throw new EmployeeIdInvalidException(
                    String.format(EmployeeConstant.EMP_ID_INVALID, empIdStr),
                    HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
            );
        }
        Long empId = Long.parseLong(empIdStr);




        EmployeeInfo exitstingEmployeeInfo = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                String.format(EmployeeConstant.EMP_NOT_FOUND, empIdStr)
                                , HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
                        ));
        //  Path path = Paths.get(exitstingEmployeeInfo.getFilePath());
//        Resource resource = new UrlResource(path.toUri());
//        if (!resource.exists()) {
//            ResponseEntity.notFound().build();
//        }
        return employeeMapper.mapEmployeeInfotoEmployeeDto(exitstingEmployeeInfo);
//return resource;
    }

    @Override
    public EmployeeDto deleteById(String empIdStr) {
        if (!Pattern.matches("^\\d+$", empIdStr)) {
            throw new EmployeeIdInvalidException(
                    String.format(EmployeeConstant.EMP_ID_INVALID, empIdStr),
                    HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
            );
        }
        Long empId = Long.parseLong(empIdStr);

        EmployeeInfo exitstingEmployeeInfo = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                String.format(EmployeeConstant.EMP_NOT_FOUND, empIdStr)
                                , HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
                        ));
        employeeRepository.deleteById(empId);
        return employeeMapper.mapEmployeeInfotoEmployeeDto(exitstingEmployeeInfo);

    }
    @Override
    public EmployeeDto updateById(String empIdStr, EmployeeDto updatedemployeeDto) {

        if (!Pattern.matches("^\\d+$", empIdStr)) {
            throw new EmployeeIdInvalidException(
                    String.format(EmployeeConstant.EMP_ID_INVALID, empIdStr),
                    HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
            );
        }
        Long empId = Long.parseLong(empIdStr);

        EmployeeInfo exitstingEmployeeInfo = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                String.format(EmployeeConstant.EMP_NOT_FOUND, empIdStr)
                                , HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
                        ));

        if (employeeRepository.existsByEmailAndEmpIdNot(updatedemployeeDto.getEmail(), empId)) {
            throw new EmailAlreadyExistsException(
                    String.format(EmployeeConstant.EMAIL_ALREADY_TAKEN, updatedemployeeDto.getEmail()),
                    HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
            );
        }

        if (employeeRepository.existsByPhoneAndEmpIdNot(updatedemployeeDto.getPhone(), empId)) {
            throw new PhoneAlreadyExistsException(
                    String.format(EmployeeConstant.PHONE_ALREADY_TAKEN, updatedemployeeDto.getPhone()),
                    HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
            );

        }
        EmployeeInfo updatedEmployeeInfo=employeeMapper.mapEmployeeDtotoEmployeeInfo(updatedemployeeDto);
        updatedEmployeeInfo.setEmpId(empId);
        EmployeeInfo newUpdatedEmployee=employeeRepository.save(updatedEmployeeInfo);

        return employeeMapper.mapEmployeeInfotoEmployeeDto(newUpdatedEmployee);
    }

    @Override
    public Resource getempByfileName(String empIdStr)throws IOException {
        if (!Pattern.matches("^\\d+$", empIdStr)) {
            throw new EmployeeIdInvalidException(
                    String.format(EmployeeConstant.EMP_ID_INVALID, empIdStr),
                    HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
            );
        }
        Long empId = Long.parseLong(empIdStr);




        EmployeeInfo exitstingEmployeeInfo = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                String.format(EmployeeConstant.EMP_NOT_FOUND, empIdStr)
                                , HttpStatus.valueOf(EmployeeConstant.BAD_REQUEST_STATUS)
                        ));

        String path1=exitstingEmployeeInfo.getFilePath();

        // Optional<EmployeeInfo> employeeInfo=employeeRepository.findByFileName(fileName);
        Path path = Paths.get(path1);
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists()) {
            ResponseEntity.notFound().build();
        }
        return resource;

    }
}
