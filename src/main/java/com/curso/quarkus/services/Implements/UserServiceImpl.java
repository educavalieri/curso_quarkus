package com.curso.quarkus.services.Implements;

import com.curso.quarkus.dtos.UserDTO;
import com.curso.quarkus.entities.User;
import com.curso.quarkus.repositories.UserRepository;
import com.curso.quarkus.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.curso.quarkus.entities.User.toDto;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id);
        return toDto(entity);
    }

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        List<User> entities = userRepository.listAll();
        return entities.stream().map(costumer -> UserDTO
                .builder()
                .email(costumer.getEmail())
                .name(costumer.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        User entity = User
                .builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        try {
            userRepository.persist(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return toDto(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
