package com.communicare.CommuniCareBackend.External.repository;

import com.communicare.CommuniCareBackend.Domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryWEB {
    List<User> findByPradeshiyaSabaha(String pradeshiyaSabaha);
}
