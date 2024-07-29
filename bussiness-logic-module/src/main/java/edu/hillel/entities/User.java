package edu.hillel.entities;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    private String login;
    private String password;
    private String name;
    private Role role;
    private String clientIPAddress;

    public enum Role {
        ADMIN, CLIENT
    }

    @Override
    public String toString() {
        return String.join("_", login, password, name, role.toString());
    }
}
