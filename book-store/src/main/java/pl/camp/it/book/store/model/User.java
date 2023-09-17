package pl.camp.it.book.store.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.context.annotation.RequestScope;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name ="tuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(int id) {
        this.id = id;
    }

    public static User copOf(User user){
        User result = new User();
        result.id = user.id;
        result.role = user.role;
        result.name = user.name;
        result.surname = user.surname;
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

