package com.sprk.employee_management.controller.advice;

import com.sprk.employee_management.dto.ErrorResponseDto;
import com.sprk.employee_management.dto.ResponseDto;
import com.sprk.employee_management.entity.EmployeeInfo;
import com.sprk.employee_management.exception.EmployeeException;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class EmployeeAdvice extends ResponseEntityExceptionHandler {

    private final View error;
    public EmployeeAdvice(View error){
        this.error=error;
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> validerrorMessage=new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        for (ObjectError objectError:allErrors){
            FieldError fieldError=(FieldError) objectError;
            String fieldName=fieldError.getField();
            String errorMessage=objectError.getDefaultMessage();
            validerrorMessage.put(fieldName,errorMessage);
        }

        // Creating Our ResponseDto Object
        ResponseDto<ErrorResponseDto<Map<String,String>>> responseDto=new ResponseDto<>();


        // Since Response Dto will accept Object of ErrorResponseDto so creating Object
        ErrorResponseDto<Map<String,String>> errorResponseDto=new ErrorResponseDto<>();

        errorResponseDto.setErrorMessage(validerrorMessage);
        errorResponseDto.setStatus(HttpStatus.valueOf(status.value()));
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(request.getDescription(true));

        responseDto.setResponse(errorResponseDto);

        return ResponseEntity.status(status).body(responseDto);
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }


    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto<String>>> handleEmployeeException(EmployeeException ex,WebRequest request){
        ResponseDto<ErrorResponseDto<String>> responseDto=new ResponseDto<>();
        ErrorResponseDto<String> errorResponseDto=new ErrorResponseDto<>();
        errorResponseDto.setErrorMessage(ex.getMessage());
        errorResponseDto.setStatus(ex.getStatus());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(request.getDescription(false));

        responseDto.setResponse(errorResponseDto);

        return ResponseEntity.status(ex.getStatus()).body(responseDto);
    }
}