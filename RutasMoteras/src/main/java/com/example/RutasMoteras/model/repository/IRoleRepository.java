package com.example.RutasMoteras.model.repository;



import com.example.RutasMoteras.model.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long>
{
    Role findByName(String name);
}
