package com.curso.quarkus.services.Implements;

import com.curso.quarkus.dtos.PostDTO;
import com.curso.quarkus.entities.Post;
import com.curso.quarkus.entities.User;
import com.curso.quarkus.services.PostService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostServiceImpl implements PostService {
    @Override
    public PostDTO findById(Long id) {
        Post entity = Post.findById(id);
        User entityUser = User.findById(entity.getUser());

        return PostDTO
                .builder()
                .message(entity.getMessage())
                .user_id(entityUser.getId())
                .build();
    }

    @Override
    @Transactional
    public List<PostDTO> findAllPosts(Long userId) {
        User entity = User.findById(userId);
        List<PostDTO> postDTOS = entity.getPosts().stream().map(post ->
                PostDTO.builder()
                .message(post.getMessage())
                .user_id(post.getUser().getId())
                .build()).collect(Collectors.toList());

        return postDTOS;

    }
    @Override
    @Transactional
    public PostDTO save(PostDTO dto) {

        User userEntity = User.findById(dto.getUser_id());
        Post entity = Post
                .builder()
                .message(dto.getMessage())
                .user(userEntity)
                .build();

        try {
            entity.persist();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dto;
    }
}
