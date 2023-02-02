package com.curso.quarkus.services;

import com.curso.quarkus.dtos.PostDTO;
import com.curso.quarkus.dtos.UserDTO;
import com.curso.quarkus.entities.Post;

import java.util.List;

public interface PostService {

    PostDTO findById(Long id);

    List<PostDTO> findAllPosts(Long userId);

    PostDTO save(PostDTO dto);

}
