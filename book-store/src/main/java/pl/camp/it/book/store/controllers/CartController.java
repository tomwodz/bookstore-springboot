package pl.camp.it.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionData;

@Controller
@RequestMapping(path="/cart")
public class CartController {

    @Resource
    SessionData sessionData;

    @Autowired
    ICartService cartService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String cart(Model model) {
        if (!this.sessionData.isLogged()) {
            return "redirect:/main";
        }
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("positions", this.sessionData.getCart().getPositions());
        model.addAttribute("total", this.cartService.calculateCartSum());
        return "cart";
    }

    @RequestMapping(path = "/add/{id}", method = RequestMethod.GET)
    public String addToCart(@PathVariable int id) {
        if (!this.sessionData.isLogged()) {
            return "redirect:/main";
        }
        this.cartService.addProductToCart(id);
        return "redirect:/main";
    }

    @RequestMapping(path = "/confirm", method = RequestMethod.GET)
    public String confirm(){
     if (!this.sessionData.isLogged() && this.sessionData.getCart().getPositions().isEmpty()) {
            return "redirect:/main";
        }
        this.cartService.confirm();
        if(!this.sessionData.getCart().getPositions().isEmpty()){
            return "redirect:/cart";
        }
        return "redirect:/main";
    }
}
