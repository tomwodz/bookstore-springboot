package pl.camp.it.book.store.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IUserService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path="/api/v1/user")
@AllArgsConstructor
public class UserRestController {

    private final IUserService userService;

    @GetMapping(path = "/{loginOrId}")
    public ResponseEntity<User> getByLogin(@PathVariable String loginOrId) {
        Optional<User> userBox;
        try {
            int id = Integer.parseInt(loginOrId);
            userBox = this.userService.getById(id);
        } catch (NumberFormatException e) {
            userBox = this.userService.getByLogin(loginOrId);
        }
        return userBox.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        user.setId(0);
        try {
            this.userService.persistUser(user);
        } catch (LoginAlreadyExistException e) {
            return ResponseEntity.status(CONFLICT).build();
        }
        return ResponseEntity.status(CREATED).body(user);
    }


}
