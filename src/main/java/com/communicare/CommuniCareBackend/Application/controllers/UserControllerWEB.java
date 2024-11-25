package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Domain.entity.User;
import com.communicare.CommuniCareBackend.Domain.service.UserServicesWEB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerWEB {

    private UserServicesWEB userServicesWEB;

    @GetMapping("/by-pradeshiya-sabaha")
    public List<User> getUsersByPradeshiyaSabaha(@RequestParam String pradeshiyaSabaha) {
        return userServicesWEB.getUsersByPradeshiyaSabaha(pradeshiyaSabaha);
    }
}
