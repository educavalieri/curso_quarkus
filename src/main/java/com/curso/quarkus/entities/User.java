package com.curso.quarkus.entities;

import com.curso.quarkus.dtos.UserDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "user_costumer")
public class User extends PanacheEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public static UserDTO toDto(User entity){
        return UserDTO
                .builder()
                .name(entity.name)
                .email(entity.email)
                .build();
    }

}

