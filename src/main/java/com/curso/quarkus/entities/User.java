package com.curso.quarkus.entities;

import com.curso.quarkus.dtos.UserDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_user")
public class User extends PanacheEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    List<Post> posts = new ArrayList<>();

    public static UserDTO toDto(User entity){
        return UserDTO
                .builder()
                .name(entity.name)
                .email(entity.email)
                .build();
    }

    public Long getId(){
        return id;
    }

}

