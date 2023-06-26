package pl.camp.it.book.store.model;

import lombok.*;
import lombok.experimental.Accessors;

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

}
