package com.sprk.student_file_upload.mapper;

import com.sprk.student_file_upload.dto.StudentDto;
import com.sprk.student_file_upload.dto.StudentFileDto;
import com.sprk.student_file_upload.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T20:06:47+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student mapDtotoInfo(StudentFileDto studentFileDto) {
        if ( studentFileDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setRollNo( studentFileDto.getRollNo() );
        student.setFirstName( studentFileDto.getFirstName() );
        student.setLastName( studentFileDto.getLastName() );
        student.setEmail( studentFileDto.getEmail() );
        student.setPhone( studentFileDto.getPhone() );

        return student;
    }

    @Override
    public StudentDto mapstudentInfotostudentDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto.StudentDtoBuilder studentDto = StudentDto.builder();

        studentDto.rollNo( student.getRollNo() );
        studentDto.firstName( student.getFirstName() );
        studentDto.lastName( student.getLastName() );
        studentDto.email( student.getEmail() );
        studentDto.phone( student.getPhone() );
        studentDto.review( student.getReview() );
        studentDto.status( student.getStatus() );
        studentDto.fileName( student.getFileName() );
        studentDto.filePath( student.getFilePath() );

        return studentDto.build();
    }

    @Override
    public Student mapStudentDtotoInfo(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setRollNo( studentDto.getRollNo() );
        student.setFirstName( studentDto.getFirstName() );
        student.setLastName( studentDto.getLastName() );
        student.setEmail( studentDto.getEmail() );
        student.setPhone( studentDto.getPhone() );
        student.setFileName( studentDto.getFileName() );
        student.setFilePath( studentDto.getFilePath() );
        student.setReview( studentDto.getReview() );
        student.setStatus( studentDto.getStatus() );

        return student;
    }
}
