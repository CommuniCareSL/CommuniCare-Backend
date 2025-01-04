package com.communicare.CommuniCareBackend.Domain.entity;

//Web App
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employees implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String email;
    private String address;
    private String nic;
    private String district;
//    private Integer sabaha;
    @ManyToOne
    @JoinColumn(name = "sabha_id", referencedColumnName = "sabhaId", nullable = false)
    private Sabha sabha;
    private String name;
    private String password;
    private String role;

//    @ManyToOne
//    @JoinColumn(name = "sabha_department_id", nullable = false)
//    private SabhaDepartment sabhaDepartmentId;

    @ManyToOne //many employees can belong to one department, each employee is associated with a single department
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId", nullable = false)
    private Department department;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
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