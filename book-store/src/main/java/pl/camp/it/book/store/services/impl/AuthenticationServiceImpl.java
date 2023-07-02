package pl.camp.it.book.store.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.session.SessionData;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    IUserRepository userRepository;

    @Resource
    SessionData sessionData;


    @Override
    public void authenticate(String login, String password) {
    User user = this.userRepository.getByLogin(login);
    if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password))){
        user.setPassword(null);
this.sessionData.setUser(user);
        };
    }

    @Override
    public void logout() {
        this.sessionData.setUser(null);
    }

    @Override
    public void register(User user) throws LoginAlreadyExistException{
        if(this.userRepository.getByLogin(user.getLogin()) != null){
            throw new LoginAlreadyExistException();
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userRepository.persistUser(user);
    }
}
