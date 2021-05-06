package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.dto.ClassLogDto;
import com.seu.edu.bd.cas.exeption.ResourceNotFoundExption;
import com.seu.edu.bd.cas.exeption.SectionNOtFoundException;
import com.seu.edu.bd.cas.model.ClassLog;
import com.seu.edu.bd.cas.model.Student;
import com.seu.edu.bd.cas.service.ClassLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/classlog")
@RequiredArgsConstructor
public class ClassLogController {
    private final ClassLogService classLogService;
    @PutMapping("/{classLogId}")
    public ResponseEntity<Object> saveClassLog(@RequestBody ClassLogDto logDto, @PathVariable long classLogId) throws SectionNOtFoundException {
        ClassLog save = classLogService.update(classLogId, logDto.getDuration(), logDto.getConductAt(), logDto.getStatus());
        return ResponseEntity.ok().body(save);
    }
    @PostMapping("/{sectionId}")
    public ResponseEntity<Object> saveClassLogTemporary(@RequestBody List<Student> students, @PathVariable String sectionId) throws SectionNOtFoundException {
        ClassLog save = classLogService.save(sectionId, students);
        return ResponseEntity.ok().body(save);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(required = true) long id) throws ResourceNotFoundExption {
         return ResponseEntity.ok().body(classLogService.findById(id));
    }
    @GetMapping("/{initial}/classlogs")
    public ResponseEntity<Object> findAllLogByInitial(@PathVariable String initial){
        return ResponseEntity.ok().body(classLogService.findClassLogByFaculty(initial));
    }

}
