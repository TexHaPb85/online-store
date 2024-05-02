package edu.hillel.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private String login;
    private String password;
    private String name;
    private Role role;

    public enum Role {
        ADMIN, CLIENT
    }
}
