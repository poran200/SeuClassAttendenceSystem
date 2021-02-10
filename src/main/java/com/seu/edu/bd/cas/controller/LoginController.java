package com.seu.edu.bd.cas.controller;

import com.seu.edu.bd.cas.anotation.CurrentUser;
import com.seu.edu.bd.cas.config.CUserDetails;
import com.seu.edu.bd.cas.exeption.ResourceNotFoundExption;
import com.seu.edu.bd.cas.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class LoginController {
    private final FacultyRepository facultyRepository;
    @GetMapping("/")
    public ResponseEntity<Object> getLogin(@CurrentUser CUserDetails currentUser) throws ResourceNotFoundExption {
          return ResponseEntity.ok().body(facultyRepository.findByInitial(currentUser.getUsername()).orElseThrow(ResourceNotFoundExption::new));
    }
}
