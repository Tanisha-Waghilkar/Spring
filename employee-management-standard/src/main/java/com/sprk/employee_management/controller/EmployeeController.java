package com.sprk.employee_management.controller;

import com.sprk.employee_management.constant.EmployeeConstant;
import com.sprk.employee_management.dto.EmployeeDto;
import com.sprk.employee_management.dto.EmployeeFileDto;
import com.sprk.employee_management.dto.ResponseDto;
import com.sprk.employee_management.dto.SuccessResponseDto;
import com.sprk.employee_management.entity.EmployeeInfo;
import com.sprk.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/employees")

    public ResponseEntity<ResponseDto<SuccessResponseDto<EmployeeDto>>> saveEmployee(@Valid @ModelAttribute EmployeeFileDto employeeFileDto){

        try {

            EmployeeDto passedEmployeeDto=employeeService.addEmployee(employeeFileDto);

            ResponseDto<SuccessResponseDto<EmployeeDto>> responseDto=new ResponseDto<>();

            SuccessResponseDto<EmployeeDto> successResponseDto=new SuccessResponseDto<>();
            successResponseDto.setMessage(String.format(EmployeeConstant.INSERT_MESSAGE,passedEmployeeDto.getEmpId()));
            successResponseDto.setData(passedEmployeeDto);
            successResponseDto.setStatus(EmployeeConstant.INSERT_STATUS);
            responseDto.setResponse(successResponseDto);

            return ResponseEntity.status(HttpStatus.valueOf(EmployeeConstant.INSERT_STATUS)).body(responseDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/employees")
    public ResponseEntity<ResponseDto<SuccessResponseDto<List<EmployeeDto>>>> getallEmployee(){
        ResponseDto<SuccessResponseDto<List<EmployeeDto>>> responseDto=new ResponseDto<>();
        List<EmployeeDto> employeeDtoList=employeeService.getallEmployee();
        SuccessResponseDto<List<EmployeeDto>> successResponseDto=new SuccessResponseDto<>();
        successResponseDto.setData(employeeDtoList);
        successResponseDto.setStatus(EmployeeConstant.SUCCESS_STATUS);
        successResponseDto.setMessage(EmployeeConstant.FETCH_ALL_MESSAGE);
        responseDto.setResponse(successResponseDto);

        return ResponseEntity.status(HttpStatus.valueOf(EmployeeConstant.SUCCESS_STATUS)).body(responseDto);
    }


    @GetMapping("/employees/{empId}")
    public ResponseEntity<ResponseDto<SuccessResponseDto<EmployeeDto>>> getempBYId(@PathVariable("empId") String empIdStr){

        EmployeeDto newgetbyId = employeeService.getempBYId(empIdStr);
        ResponseDto<SuccessResponseDto<EmployeeDto>> responseDto = new ResponseDto<>();
        SuccessResponseDto<EmployeeDto> successResponseDto = new SuccessResponseDto<>();
        successResponseDto.setMessage(String.format(EmployeeConstant.FETCH_EMP_MESSAGE));
        successResponseDto.setData(newgetbyId);
        successResponseDto.setStatus(EmployeeConstant.SUCCESS_STATUS);
        responseDto.setResponse(successResponseDto);

        return ResponseEntity.status(HttpStatus.valueOf(EmployeeConstant.SUCCESS_STATUS)).body(responseDto);

    }

    @GetMapping("/download/{empId}")
    public ResponseEntity<Resource> getempByfileName(@PathVariable("empId") String empIdStr){
        try {
            Resource newFile=employeeService.getempByfileName(empIdStr);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+newFile.getFilename()+"\"").body(newFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<ResponseDto<SuccessResponseDto<EmployeeDto>>> deleteById(@PathVariable("empId") String empIdStr){
        EmployeeDto employeeDto = employeeService.deleteById(empIdStr);
        ResponseDto<SuccessResponseDto<EmployeeDto>> responseDto=new ResponseDto<>();
        SuccessResponseDto<EmployeeDto> successResponseDto=new SuccessResponseDto<>();
        successResponseDto.setStatus(EmployeeConstant.SUCCESS_STATUS);
        successResponseDto.setData(employeeDto);
        successResponseDto.setMessage(String.format(EmployeeConstant.EMP_DELETE_MESSAGE,empIdStr));
        responseDto.setResponse(successResponseDto);
        return ResponseEntity.status(HttpStatus.valueOf(EmployeeConstant.SUCCESS_STATUS)).body(responseDto);
    }

    @PutMapping("/employees/{empId}")
    public ResponseEntity<ResponseDto<SuccessResponseDto<EmployeeDto>>> updateempById(@PathVariable("empId") String empIdStr,@RequestBody EmployeeDto employeeDto){
        EmployeeDto newUpdateEmployeeDto= employeeService.updateById(empIdStr,employeeDto);
        ResponseDto<SuccessResponseDto<EmployeeDto>> responseDto=new ResponseDto<>();
        SuccessResponseDto<EmployeeDto> successResponseDto=new SuccessResponseDto<>();
        successResponseDto.setMessage(String.format(EmployeeConstant.EMP_UPDATE_MESSAGE,empIdStr));
        successResponseDto.setData(newUpdateEmployeeDto);
        successResponseDto.setStatus(EmployeeConstant.SUCCESS_STATUS);
        responseDto.setResponse(successResponseDto);
        return ResponseEntity.status(HttpStatus.valueOf(EmployeeConstant.SUCCESS_STATUS)).body(responseDto);
    }
    @GetMapping("/security/hello")
    public String hello(){

        logger.info("Hello Api Called");
        logger.debug("This is admin login");
        System.out.println("This is admin");
        return "hello logging";

    }
    @GetMapping("/security/hi")
    public String hi(){

        logger.info("hi Api Called");
        logger.debug("This is user login");
        System.out.println("This is user");
        return "hi logging";

    }
}