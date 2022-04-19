package com.example.restblog.data;

import lombok.*;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public enum Role {USER, ADMIN}

    private long id;
    private Collection<Post> posts;
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
    private Role role;
}
