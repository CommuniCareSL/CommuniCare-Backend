package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Integer> {


    Optional<Users> findByEmail(String email);
}
