package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.Cart;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionData;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    SessionData sessionData;

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void addProductToCart(final int id) {
        Optional<Book> bookBox = this.bookDAO.getBookById(id);
        if (bookBox.isEmpty()) {
            return;
        }
        final Book book = bookBox.get();
        Cart cart = this.sessionData.getCart();
        for (OrderPosition orderPosition : cart.getPositions()) {
            if (orderPosition.getBook().getId() == id) {
                if (orderPosition.getQuantity() < book.getQuantity()) {
                    orderPosition.incrementQuantity();
                }
                return;
            }
        }
        if (book.getQuantity() > 0) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setBook(book);
            orderPosition.setQuantity(1);
            cart.getPositions().add(orderPosition);
        }
    }

    @Override
    public void confirm() {
        Map<Book, Integer> booksToUpdateWithNewQuantity = new HashMap<>();
        boolean positionChanged = false;
        for (OrderPosition orderPosition : this.sessionData.getCart().getPositions()) {
            Optional<Book> bookFromDbBox = this.bookDAO.getBookById(orderPosition.getBook().getId());
            if (bookFromDbBox.isEmpty()) {
                this.sessionData.getCart().getPositions().remove(orderPosition);
                return;
            }
            Book bookFromDb = bookFromDbBox.get();
            if (bookFromDb.getQuantity() < orderPosition.getQuantity()) {
                orderPosition.setQuantity(bookFromDb.getQuantity());
                positionChanged = true;
            }
            booksToUpdateWithNewQuantity.put(bookFromDb, bookFromDb.getQuantity() - orderPosition.getQuantity());
        }
        if (positionChanged) {
            return;
        }
        final Order order = new Order();
        order.setUser(this.sessionData.getUser());
        this.sessionData.getCart().getPositions()
                .stream()
                .peek(op ->
                {
                    Book book = op.getBook();
                    book.setQuantity(booksToUpdateWithNewQuantity.get(book));
                })
                .forEach(order::addOrderPosition);
        order.setStatus(Order.Status.NEW);
        order.setTotal(this.calculateCartSum());
        order.setDateTime(LocalDateTime.now());
        this.orderDAO.persistOrder(order);
        this.clearCart();
    }

    @Override
    public void removeFromCart(final int id) {
        this.sessionData.getCart().getPositions().stream()
                .filter(orderPosition -> orderPosition.getBook().getId() == id)
                .findFirst()
                .ifPresent(op -> this.sessionData.getCart().getPositions().remove(op));
    }

    @Override
    public void clearCart() {
        this.sessionData.getCart().getPositions().clear();
    }

    @Override
    public double calculateCartSum() {
        return this.sessionData.getCart().getPositions().stream()
                .mapToDouble(op -> op.getQuantity() * op.getBook().getPrice())
                .sum();
    }
}
