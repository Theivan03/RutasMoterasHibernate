package com.example.RutasMoteras.dto.User.Athentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {

    private String name;

    private String password;

    private String surname;

    private String email;

    private String city;

    private String postalCode;

    private String image;

    private String username;

}
