package com.example.RutasMoteras.controllers;

import com.example.RutasMoteras.exceptions.Response;
import com.example.RutasMoteras.mappers.UserMapper;
import com.example.RutasMoteras.model.entity.Role;
import com.example.RutasMoteras.model.entity.Ruta;
import com.example.RutasMoteras.model.entity.User;
import com.example.RutasMoteras.model.service.Ruta.IRutaService;
import com.example.RutasMoteras.model.service.User.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class RutasMoterasController {

    @Autowired
    private IRutaService rutaService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(RutasMoterasController.class);


    @Operation(summary = "Método que crea una ruta")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Ruta creada",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(   responseCode = "404", description = "Ruta no creada",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @PostMapping("/ruta")
    public ResponseEntity<Ruta> AddRuta(@RequestBody Ruta ruta)
    {
        return new ResponseEntity<Ruta>(rutaService.addRuta(ruta), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas las rutas")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/rutas")
    public ResponseEntity<List<Ruta>> GetAllRutas()
    {
        return new ResponseEntity<>(rutaService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve una ruta")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Ruta devuenta",
                    content = @Content(schema = @Schema(implementation = Ruta.class))),
            @ApiResponse(   responseCode = "404", description = "Ruta no devuenta",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/ruta/{id}")
    public ResponseEntity<Ruta> GetRutaById(@PathVariable Long id)
    {
        return new ResponseEntity<Ruta>(rutaService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Método que borra una ruta")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Ruta borrada por id",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(   responseCode = "404", description = "Ruta no borrada",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @DeleteMapping("/ruta/{id}")
    public ResponseEntity<Response> deleteRuta(@PathVariable Long id)
    {
        Map<String, String> errores = new HashMap<>();

        try
        {
            rutaService.delete(id);

            logger.info("Mecanico borrado correctamente: " + id);
        }
        catch (Exception ex)
        {
            logger.error("Error al borrar la ruta con el id: " + id, ex);

            errores.put("mensaje", "Error al borrar la ruta con el id: " + id);

            return new ResponseEntity<>(Response.generalError(errores), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(Response.noErrorResponse("Ruta borrada correctamente"), HttpStatus.OK);
    }

    @Operation(summary = "Método que actualiza una ruta")
        @PutMapping("/ruta/{id}")
        public ResponseEntity<?> updateRuta(@PathVariable Long id, @Valid @RequestBody Ruta newRuta){

            Ruta ruta = null;
            Map<String, Object> errores = new HashMap<String, Object>();

            try{

                ruta = rutaService.update(id, newRuta);

            }catch (Exception ex){

                errores.put("Mensaje", "Error al obtener la ruta");
                errores.put("Mensaje", ex.getMessage());
                return new ResponseEntity<>(errores, HttpStatus.INTERNAL_SERVER_ERROR);

            }

            return new ResponseEntity<>(ruta, HttpStatus.OK);
    }


    @Operation(summary = "Método que devuelve todas las rutas filtrando por tipo de moto")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/rutasT/{id}")
    public ResponseEntity<List<Ruta>> GetAllRutasFiltradoTipoMoto(@PathVariable String id)
    {
        return new ResponseEntity<>(rutaService.filtrarPorTipoMoto(id), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas las rutas filtrando por comunidad")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/rutasC/{id}")
    public ResponseEntity<List<Ruta>> GetAllRutasFiltradoComuinidad(@PathVariable String id)
    {
        return new ResponseEntity<>(rutaService.filtrarPorComunidad(id), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas las rutas filtrando por fecha más reciente")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/rutasF")
    public ResponseEntity<List<Ruta>> GetAllRutasFiltradoFecha()
    {
        return new ResponseEntity<>(rutaService.findRutaByFecha(), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas las rutas filtrando por email")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/usuario/{email}")
    public ResponseEntity<User> GetUserByEmail(@PathVariable String email)
    {
        return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve el usuario buscado por id")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/usuarioI/{id}")
    public ResponseEntity<User> GetUserById(@PathVariable Long id)
    {
        return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas las rutas filtrando por usuario")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Rutas devuentas",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Rutas no devuentas",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/rutasU/{id}")
    public ResponseEntity<List<Ruta>> GetAllRutasUser(@PathVariable Long id)
    {
        return new ResponseEntity<>(rutaService.findRutaByUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id)
    {
        Map<String, String> errores = new HashMap<>();

        try
        {
            userService.remove(id);

            logger.info("Usuario borrado correctamente: " + id);
        }
        catch (Exception ex)
        {
            logger.error("Error al borrar el usuario con el id: " + id, ex);

            errores.put("mensaje", "Error al borrar el usuario con el id: " + id + ex);

            return new ResponseEntity<>(Response.generalError(errores), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(Response.noErrorResponse("Ruta borrada correctamente"), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas los usuarios")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Usuairos devuentos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Usuairos no devuentos",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/allUser")
    public ResponseEntity<List<User>> GetAllUsers()
    {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Método que devuelve todas los usuarios")
    @ApiResponses(value = {
            @ApiResponse(   responseCode = "200", description = "Usuairos devuentos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ruta.class)))),
            @ApiResponse(   responseCode = "404", description = "Usuairos no devuentos",
                    content = @Content(schema =  @Schema(implementation = Response.class)))
    })
    @GetMapping("/roleUser/{id}")
    public ResponseEntity<List<Role>> GetRoleForUser(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.getRol(id), HttpStatus.OK);
    }

}
