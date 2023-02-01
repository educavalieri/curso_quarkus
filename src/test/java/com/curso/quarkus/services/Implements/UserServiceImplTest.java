package com.curso.quarkus.services.Implements;

import com.curso.quarkus.dtos.UserDTO;
import com.curso.quarkus.entities.User;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
class UserServiceImplTest {

    @Inject
    UserServiceImpl userService;

    @Test
    void findByIdShouldReturnsDto(){

        PanacheMock.mock(User.class);
        User user = new User("Edu", "bla");

        Mockito.when(User.findById(Mockito.anyLong())).thenReturn(user);

        UserDTO response = userService.findById(1L);

        Assertions.assertEquals("name", response.getName());
        Assertions.assertEquals("name@email", response.getEmail());

    }



}