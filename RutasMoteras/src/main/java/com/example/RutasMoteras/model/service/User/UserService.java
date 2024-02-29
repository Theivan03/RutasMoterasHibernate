package com.example.RutasMoteras.model.service.User;


import com.example.RutasMoteras.model.entity.Role;
import com.example.RutasMoteras.model.entity.User;
import com.example.RutasMoteras.model.repository.IRoleRepository;
import com.example.RutasMoteras.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService
{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByCity(String city) {
        return null;
    }

    @Override
    public User addUser(User user, List<Long> rolIds)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setRoles((List<Role>)roleRepository.findAllById(rolIds));

        return userRepository.save(user);
    }
}
