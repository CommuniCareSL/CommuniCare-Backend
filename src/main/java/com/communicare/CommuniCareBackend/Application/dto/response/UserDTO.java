package com.communicare.CommuniCareBackend.Application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Data //getters and setters
public class UserDTO {

    private int id;
    private String name;
    private String address;
}

//example


