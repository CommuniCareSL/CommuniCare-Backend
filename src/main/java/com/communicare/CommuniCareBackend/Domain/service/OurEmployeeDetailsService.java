package com.communicare.CommuniCareBackend.Domain.service;

import com.communicare.CommuniCareBackend.External.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeesRepo employeesRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeesRepo.findByEmail(username).orElseThrow();
    }

}
