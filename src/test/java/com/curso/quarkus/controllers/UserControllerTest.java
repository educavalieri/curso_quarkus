package com.curso.quarkus.controllers;

import com.curso.quarkus.dtos.UserDTO;
import com.curso.quarkus.services.UserService;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class UserControllerTest {

    private static final String JSON_ENTRY = "{\"name\": \"name\", \"email\": \"name@email\"}";

    UserDTO userDTO = UserDTO
            .builder()
            .name("name")
            .email("name@email")
            .build();

    List<UserDTO> dtos = new ArrayList<>();


    @TestHTTPResource("/user")
    URL apiURL;

    @InjectMock
    UserService userService;

    @BeforeEach
    void setup() throws Exception{

        dtos.add(userDTO);
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(userDTO);
        Mockito.when(userService.findAll()).thenReturn(dtos);
        Mockito.when(userService.save(Mockito.any())).thenReturn(userDTO);

    }

    @Test
    @DisplayName("should returns UserDto")
    void findByIdShouldReturnsUserDto() {

        var response =
                given()
                        .when()
                        .get(apiURL + "/1")
                        .then()
                        .extract()
                        .response();

        Assertions.assertEquals(200, response.getStatusCode());

    }

    @Test
    @DisplayName("should returns List")
    void listAllShouldReturnsListUserDto() {

        var response =
                given()
                        .when()
                        .get(apiURL)
                        .then()
                        .extract()
                        .response();

        Assertions.assertEquals(200, response.getStatusCode());

    }

    @Test
    @DisplayName("should Persist and returns UserDTO")
    void saveShouldPersistAndReturnsUserDto() {

        var response =
        given()
                .contentType(ContentType.JSON)
                .body(JSON_ENTRY)
                .when()
                .post(apiURL)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("{\"email\":\"name@email\",\"name\":\"name\"}", response.getBody().print());

    }


}