package com.curso.quarkus.controllers;

import com.curso.quarkus.dtos.UserDTO;
import com.curso.quarkus.services.UserService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        UserDTO dto = userService.findById(id);
        return Response.ok(dto).build();
    }

    @GET
    public Response listAll(){
        List<UserDTO> dtos = userService.findAll();
        return Response.ok(dtos).build();
    }

    @POST
    public Response save(UserDTO dto){
        UserDTO newDto = userService.save(dto);
        return Response.ok(newDto).build();
    }


}
