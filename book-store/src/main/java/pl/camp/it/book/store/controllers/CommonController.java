package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.book.store.services.IBookService;
import pl.camp.it.book.store.session.SessionData;

@Controller
public class CommonController {

    @Autowired
    private IBookService bookService;


    @Resource
    SessionData sessionData;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Model model,
                       @RequestParam(required = false) String pattern){
     if(pattern== null) {
            model.addAttribute("books", this.bookService.getAllBooks());
        } else {
            model.addAttribute("books", this.bookService.getByPattern(pattern));
        }
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "index";
    }

    @RequestMapping(path = {"/","/test"}, method = RequestMethod.GET)
    public String main(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "redirect:/main";
    }

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "contact";
    }
}

