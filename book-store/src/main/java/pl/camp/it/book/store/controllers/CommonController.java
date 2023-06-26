package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.book.store.database.IAuthenticationService;
import pl.camp.it.book.store.database.memory.AuthenticationServiceImpl;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;
import pl.camp.it.book.store.session.SessionData;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private IBookService bookService;

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionData sessionData;

    //private List<Book> filteredBooks = null;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Model model,
                       @RequestParam(required = false) String pattern){
        if(!this.sessionData.isLogged()){
            model.addAttribute("books", new ArrayList<>());
        }
        else if(pattern== null) {
            model.addAttribute("books", this.bookService.getAllBooks());
        } else {
            model.addAttribute("books", this.bookService.getFilteredBooks(pattern));
        }
        return "index";
    }

    @RequestMapping(path = {"/","/test"}, method = RequestMethod.GET)
    public String main(){
        return "redirect:/main";
    }



   /* @RequestMapping(path= "/main", method = RequestMethod.POST)
    public String main(Model model,
                       @RequestParam String pattern){
            this.filteredBooks =  this.bookService.getFilteredBooks(pattern);
        return "redirect:/main";
    }*/

   /* @RequestMapping(path= "/main", method = RequestMethod.POST)
    public String main(Model model,
                       @RequestParam String pattern){
    *//*    model.addAttribute("books", this.bookService.getFilteredBooks(pattern));*//*
        return "redirect:/main";
    }*/

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact(){
        return "contact";
    }
}

