package com.curso.quarkus.dtos;

import com.curso.quarkus.entities.Post;
import com.curso.quarkus.entities.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {

    private String message;
    private Long user_id;


}
