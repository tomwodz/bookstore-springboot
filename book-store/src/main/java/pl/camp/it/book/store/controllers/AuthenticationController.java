package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.exception.UserValidationException;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.session.SessionData;
import pl.camp.it.book.store.validators.UserValidator;

@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionData sessionData;

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(Model model
    ){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "login";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password){
        try {
            UserValidator.validateLogin(login);
            UserValidator.validatePassword(password);
            this.authenticationService.authenticate(login, password);
            if(sessionData.isLogged()){
                return "redirect:/main";
            }
        }
        catch (UserValidationException e){}
        return "redirect:/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.authenticationService.logout();
        return "redirect:/main";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("logged", this.sessionData.isLogged());
        model.addAttribute("userModel", new User());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2){
        try {
            UserValidator.validateUser(user);
            UserValidator.validatePasswordsEquality(user.getPassword(), password2);
            this.authenticationService.register(user);
        } catch (LoginAlreadyExistException | UserValidationException e){
            return "redirect:/register";
        }
        return "redirect:/login";
    }


}
