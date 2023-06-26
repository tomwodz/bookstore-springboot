package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.book.store.database.IAuthenticationService;
import pl.camp.it.book.store.session.SessionData;

@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionData sessionData;

    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password){
        this.authenticationService.authenticate(login, password);
        if(sessionData.isLogged()){
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.authenticationService.logout();
        return "redirect:/main";
    }
}
