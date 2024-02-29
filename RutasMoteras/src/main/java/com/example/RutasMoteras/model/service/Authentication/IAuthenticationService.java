package com.example.RutasMoteras.model.service.Authentication;


import com.example.RutasMoteras.model.entity.User;

public interface IAuthenticationService
{
    public User signup(User newUser);

    public User authenticate(User user);
}
