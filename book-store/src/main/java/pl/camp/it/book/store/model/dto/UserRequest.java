package pl.camp.it.book.store.model.dto;

import lombok.*;
import pl.camp.it.book.store.model.User;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRequest {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private User.Role role;

}
