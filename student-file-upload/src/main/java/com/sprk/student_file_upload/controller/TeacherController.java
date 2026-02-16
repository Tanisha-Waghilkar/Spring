package com.sprk.student_file_upload.controller;

import com.sprk.student_file_upload.dto.ReviewDto;
import com.sprk.student_file_upload.dto.StudentDto;
import com.sprk.student_file_upload.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final StudentService studentService;

    // 🔹 Teacher: Get all students
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getallStudent();
        return ResponseEntity.ok(students);
    }

    // 🔹 Teacher: Get single student
    @GetMapping("/student/{rollNo}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String rollNo) {
        StudentDto student = studentService.getById(rollNo);
        return ResponseEntity.ok(student);
    }

    // 🔹 Teacher: Download assignment file
    @GetMapping("/student/download/{rollNo}")
    public ResponseEntity<Resource> downloadAssignment(@PathVariable String rollNo) throws IOException {

        Resource resource = studentService.downloadFile(rollNo);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // 🔹 Teacher: Review + Update status
    @PutMapping("/student/review/{rollNo}")
    public ResponseEntity<StudentDto> reviewStudent(
            @PathVariable String rollNo,
            @RequestBody ReviewDto reviewDto) {

        StudentDto updatedStudent = studentService.updateStudent(rollNo, reviewDto);
        return ResponseEntity.ok(updatedStudent);
    }
}
