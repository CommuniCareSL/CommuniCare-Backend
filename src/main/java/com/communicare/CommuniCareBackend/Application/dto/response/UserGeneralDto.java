package com.communicare.CommuniCareBackend.Application.dto.response;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserGeneralDto {

    private int id;
    private String Name;
}

// example