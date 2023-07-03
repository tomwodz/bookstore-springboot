package pl.camp.it.book.store.services.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.session.SessionData;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    SessionData sessionData;
    @Autowired
    IOrderDAO orderDAO;
    @Override
    public List<Order> getAllOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionData.getUser().getId());
    }
}
