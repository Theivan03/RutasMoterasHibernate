package com.example.RutasMoteras.dto.User.Athentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO
{
    private String password;

    private String name;

    private String surname;

    private String email;

    private String image;

    private List<Long> rolIds;

    private String city;

    private String postalCode;
}
