package com.example.RutasMoteras.model.service.User;



import com.example.RutasMoteras.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService
{
    List<User> findAll();
    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    Optional<User> findById(Long id);
    List<User> findByCity(String city);

    User addUser(User user, List<Long> rolIds);
    void remove(User user);
}
