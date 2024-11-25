package com.communicare.CommuniCareBackend.Domain.service;


import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.External.repository.UserRepositoryWEB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesWEB {
    private UserRepositoryWEB userRepositoryWEB;

    public List<User> getUsersByPradeshiyaSabaha(String pradeshiyaSabaha) {
        return userRepositoryWEB.findByPradeshiyaSabaha(pradeshiyaSabaha);
    }
}
