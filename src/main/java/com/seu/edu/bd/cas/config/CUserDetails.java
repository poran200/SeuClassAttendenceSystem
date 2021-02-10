package com.seu.edu.bd.cas.config;

import com.seu.edu.bd.cas.model.Faculty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CUserDetails  implements UserDetails {
    private Faculty faculty;

    public CUserDetails(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(faculty.getRole()));
    }

    @Override
    public String getPassword() {
        return faculty.getPassword();
    }

    @Override
    public String getUsername() {
        return faculty.getInitial();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
