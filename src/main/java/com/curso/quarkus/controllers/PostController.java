package com.curso.quarkus.controllers;

import com.curso.quarkus.dtos.PostDTO;
import com.curso.quarkus.services.PostService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostController {

    @Inject
    PostService postService;

    @GET
    @Path("/{id}")
    public Response findPostsByUserId(@PathParam("id") Long id){
        List<PostDTO> postDTOList = postService.findAllPosts(id);
        return Response.ok(postDTOList).build();
    }

    @POST
    public Response save(@RequestBody PostDTO dto){
        PostDTO postDTOSave = postService.save(dto);
        return Response.ok(postDTOSave).build();
    }


}
