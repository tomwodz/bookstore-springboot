package pl.camp.it.book.store.database;

public interface IAuthenticationService {
    boolean authenticateWithReturn(String login, String password);

    void authenticate(String login, String password);

    void logout();
}
