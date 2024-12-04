package com.communicare.CommuniCareBackend.Application.dto.response;

import com.communicare.CommuniCareBackend.Domain.entity.SabhaDepartment;
import lombok.Data;

@Data
public class OfficerProfile {
    private Integer employeeId;
    private String email;
    private String address;
    private String nic;
    private String district;
    private Integer sabaha;
    private String name;
    private String role;
    private Integer sabhaDepartmentId;



    //    public OfficerProfile(String name, String email, String district, Integer sabaha, SabhaDepartment sabhaDepartmentId) {
    //    }
}
