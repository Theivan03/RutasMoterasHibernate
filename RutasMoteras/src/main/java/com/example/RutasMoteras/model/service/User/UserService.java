package com.example.RutasMoteras.model.service.User;


import com.example.RutasMoteras.model.entity.Role;
import com.example.RutasMoteras.model.entity.Ruta;
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
    public void remove(Long userid) {

        userRepository.deleteById(userid);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
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

    @Override
    public User update(Long id, User user) throws Exception {
        User userAntiguo = userRepository.findById(id).orElseThrow(() -> new Exception("Error al autenticar el usuario: " + id));
        user.setId(userAntiguo.getId());
        user.setCreationDate((userAntiguo.getCreationDate()));

        List<Role> role = userRepository.rolesUser(userAntiguo.getId());
        user.setRoles(role);
        return userRepository.save(user);
    }

    @Override
    public List<Role> getRol(Long id) {
        return userRepository.rolesUser(id);
    }
}
