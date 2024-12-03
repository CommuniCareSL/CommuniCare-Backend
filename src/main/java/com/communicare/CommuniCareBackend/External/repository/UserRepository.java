package com.communicare.CommuniCareBackend.External.repository;

//Mobile App

import com.communicare.CommuniCareBackend.Domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

//public interface UserRepository extends JpaRepository<User, UUID> (this before one complaint)
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdNumber(String idNumber);

}