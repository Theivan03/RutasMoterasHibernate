package com.example.RutasMoteras.model.repository;



import com.example.RutasMoteras.model.entity.Role;
import com.example.RutasMoteras.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long>
{
    User findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByCity(String city);
    List<User> findByPostalCode(String postalCode);

    @Query("select u.roles from user u inner join u.roles where u.id = ?1")
    List<Role> rolesUser(long idUsuario);




}
