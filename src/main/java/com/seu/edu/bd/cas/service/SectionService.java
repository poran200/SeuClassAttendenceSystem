package com.seu.edu.bd.cas.service;

import com.seu.edu.bd.cas.dto.StudentDto;
import com.seu.edu.bd.cas.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SectionService {
    private SectionRepository sectionRepository;
    private ModelMapper modelMapper;

    public Set<Object> findBySection(String section){
        Set<StudentDto> students = new HashSet<>();
        //todo
         return Collections.emptySet();
    }

}
