package com.communicare.CommuniCareBackend.Application.controllers;

import com.communicare.CommuniCareBackend.Application.dto.response.UserDTO;
import com.communicare.CommuniCareBackend.Application.dto.response.UserGeneralDto;
import com.communicare.CommuniCareBackend.Domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<UserGeneralDto> getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public String addUser() {
        return "User added";
    }

    //Custom SQL
//    @GetMapping("/getUserByUserId/{userID}")
//    public UserDTO getUserByUserID(@PathVariable String userID){
//        return userService.getUserByUserID(userID);
//    }

    @GetMapping("/getUserByUserIDAndAddress/{userID}/{address}")
    public String getUserByUserIDAndAddress(@PathVariable String userID ,@PathVariable String address){
        System.out.println("User ID :"+ userID +"User address :" +address);
        return "Success";
    }
}
// example