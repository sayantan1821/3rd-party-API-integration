package com.example.demoapi.dtos;

import com.example.demoapi.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private boolean verified;
    private List<Role> roles;
    private Date createdAt;
    private Date updatedAt;
}
