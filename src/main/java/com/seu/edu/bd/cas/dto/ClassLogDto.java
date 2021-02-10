package com.seu.edu.bd.cas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seu.edu.bd.cas.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ClassLogDto {
    private int duration;
    @JsonFormat(pattern = "mm-dd-yyy")
    private  Date conductAt;
    private  String status;
    List<Student> students;
}
