package com.seu.edu.bd.cas.service;

import com.seu.edu.bd.cas.config.CUserDetails;
import com.seu.edu.bd.cas.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetalisService implements UserDetailsService {
    private final FacultyRepository facultyRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return facultyRepository.findByInitial(s)
                .map(CUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User name not found "+s));
    }
}
