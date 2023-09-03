package pl.camp.it.book.store.controllers.rest;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionData;

@RestController
@RequestMapping(path = "/api/v1/cart")
public class CartRestController {

    @Resource
    SessionData sessionData;

    @Autowired
    ICartService cartService;

    @GetMapping(path="/add/{id}")
    public void addToCard(@PathVariable int id){
        if(!this.sessionData.isLogged()){
            return;
        }
        this.cartService.addProductToCart(id);
    }
}
