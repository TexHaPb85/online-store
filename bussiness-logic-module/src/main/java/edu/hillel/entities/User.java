package edu.hillel.entities;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User {

    private String login;
    private String password;
    private String name;
    private Role role;

    public enum Role {
        ADMIN, CLIENT
    }

    @Override
    public String toString() {
        return String.join("_", login, password, name, role.toString());
    }
}
