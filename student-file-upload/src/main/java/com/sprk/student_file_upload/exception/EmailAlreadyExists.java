package com.sprk.student_file_upload.exception;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String message){
        super(message);
    }

}
