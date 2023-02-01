package com.curso.quarkus.dtos;

import com.curso.quarkus.entities.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserDTO {

    private String name;
    private String email;

    public static User toEntity(UserDTO dto){
        return User
                .builder()
                .name(dto.name)
                .email(dto.email)
                .build();
    }
}
