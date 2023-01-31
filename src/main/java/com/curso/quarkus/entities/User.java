package com.curso.quarkus.entities;

import com.curso.quarkus.dtos.UserDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

