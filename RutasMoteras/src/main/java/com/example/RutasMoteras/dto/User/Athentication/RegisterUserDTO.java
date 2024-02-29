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

    private String nif;

    private String name;

    private String surname;

    private String email;

    private String address;

    private String city;

    private String postalCode;

    private String province;

    private String country;

    private String image;

    private List<Long> rolIds;
}
