package com.example.RutasMoteras.mappers;



import com.example.RutasMoteras.dto.User.Athentication.LoginUserDTO;
import com.example.RutasMoteras.dto.User.Athentication.RegisterUserDTO;
import com.example.RutasMoteras.dto.User.Athentication.UpdateUser;
import com.example.RutasMoteras.dto.User.UserDTORequest;
import com.example.RutasMoteras.model.entity.User;
import com.example.RutasMoteras.model.repository.IRoleRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper
{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IRoleRepository roleRepository;

    public User fromDTO(LoginUserDTO userDTO)
    {
        return modelMapper.map(userDTO, User.class);
    }

    public User fromDTO(UpdateUser userDTO)
    {
        modelMapper.typeMap(RegisterUserDTO.class, User.class).addMappings(mapper -> {
            mapper.using(new RolesListConverter(roleRepository)).map(RegisterUserDTO::getRolIds, User::setRoles);
        });

        return modelMapper.map(userDTO, User.class);
    }

    public User fromDTO(UserDTORequest userDTO)
    {
        return modelMapper.map(userDTO, User.class);
    }

    public User fromDTO(RegisterUserDTO registerUserDTO)
    {
        modelMapper.typeMap(RegisterUserDTO.class, User.class).addMappings(mapper -> {
            mapper.using(new RolesListConverter(roleRepository)).map(RegisterUserDTO::getRolIds, User::setRoles);
        });

        return modelMapper.map(registerUserDTO, User.class);
    }
}
