package com.example.RutasMoteras.controllers;


import com.example.RutasMoteras.dto.User.Athentication.LoginUserDTO;
import com.example.RutasMoteras.dto.User.Athentication.RegisterUserDTO;
import com.example.RutasMoteras.dto.User.Athentication.UpdateUser;
import com.example.RutasMoteras.exceptions.Response;
import com.example.RutasMoteras.mappers.UserMapper;
import com.example.RutasMoteras.model.entity.Ruta;
import com.example.RutasMoteras.model.entity.User;
import com.example.RutasMoteras.model.service.Authentication.IAuthenticationService;
import com.example.RutasMoteras.model.service.User.IUserService;
import com.example.RutasMoteras.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController
{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Response> register(@RequestBody RegisterUserDTO registerUserDto)
    {
        User user = userMapper.fromDTO(registerUserDto);

        authenticationService.signup(user);

        String message = "Usuario registrado correctamente";

        return new ResponseEntity<Response>(Response.noErrorResponse(message), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginUserDTO loginUserDto)
    {
        User loginUser = userMapper.fromDTO(loginUserDto);

        User authenticatedUser = authenticationService.authenticate(loginUser);

        String jwtToken = jwtTokenProvider.generateToken(authenticatedUser);

        //LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(jwtToken);
    }

    @Operation(summary = "Método que actualiza un usuario")
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateRuta(@PathVariable Long id, @Valid @RequestBody UpdateUser newUserDTO){

        User user = null;
        Map<String, Object> errores = new HashMap<String, Object>();

        String message = "Usuario modificado correctamente";

        try{

            User newUser = userMapper.fromDTO(newUserDTO);
            user = userService.update(id, newUser);

        }catch (Exception ex){

            errores.put("Mensaje", "Error al obtener el usuario");
            errores.put("Mensaje", ex.getMessage());
            return new ResponseEntity<>(errores, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
