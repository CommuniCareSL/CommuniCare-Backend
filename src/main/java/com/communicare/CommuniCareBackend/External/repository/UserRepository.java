package com.communicare.CommuniCareBackend.External.repository;

//Mobile App

import com.communicare.CommuniCareBackend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdNumber(String idNumber);

    //web application
    //to retrieve data from user table by pradeshiyasabha
    List<User> findByPradeshiyaSabaha(String pradeshiyaSabaha);
}