package com.curso.quarkus.services;

import com.curso.quarkus.dtos.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    UserDTO save(UserDTO dto);

    void delete (Long id);

}
