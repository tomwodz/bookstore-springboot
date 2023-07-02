package pl.camp.it.book.store.model;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Role role;

    public static User copOf(User user){
        User result = new User();
        result.id = user.id;
        result.role = user.role;
        result.name = user.name;
        result.surname = user.name;
        result.login = user.login;
        result.password = user.password;
        result.email = user.email;
        return result;
    }

    public enum Role {
        ADMIN,
        USER
    }
}

